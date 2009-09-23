/*
 * Copyright (c) 2009 Carl Sziebert
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * $Id$
 */
package net.sziebert.tutorials.web;

import net.sziebert.tutorials.entity.User;
import net.sziebert.tutorials.security.BCrypt;
import net.sziebert.tutorials.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ResetPasswordController {

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordController.class);

    private final UserService service;

    @Autowired
    public ResetPasswordController(UserService service) {
        this.service = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(new String[]{"confirm", "password", "username"});
    }

    @ModelAttribute("form")
    public ResetPasswordForm populateForm(@RequestParam("username") String username) {
        return new ResetPasswordForm(username);
    }

    @RequestMapping(value = "/password/reset.do", method = GET)
    public String resetPassword(HttpServletRequest request, @RequestParam("frob") String frob) {
        logger.debug("Rendering reset password form.");
        Cookie cookie = null;
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals("frob")) {
                cookie = c;
            }
        }
        return isValidCookie(cookie, frob) ? "reset-password" : "reset-expired";
    }

    @RequestMapping(value = "/password/reset.do", method = POST)
    public String processSubmit(HttpServletRequest request,
                                HttpServletResponse response,
                                @ModelAttribute("form") ResetPasswordForm form,
                                BindingResult result) {
        logger.debug("Processing reset password form.");
        User user = service.findByUsername(form.getUsername());
        new ResetPasswordFormValidator().validate(form, result);
        if (!result.hasErrors()) {
            user.setPassword(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt()));
            service.update(user);
            request.getSession().setAttribute("message", "You have successfully reset your password.");
            return "redirect:/login.do";
        }
        return "reset-password";
    }

    private boolean isValidCookie(Cookie cookie, final String frob) {
        return cookie != null && cookie.getValue().equals(frob);
    }
}