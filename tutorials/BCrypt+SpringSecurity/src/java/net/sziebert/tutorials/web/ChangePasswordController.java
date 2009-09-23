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
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangePasswordController {

    private static final Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);

    private final UserService service;

    @Autowired
    public ChangePasswordController(UserService service) {
        this.service = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(new String[]{"confirm", "original", "password"});
    }

    @ModelAttribute("form")
    public ChangePasswordForm populateForm() {
        return new ChangePasswordForm();
    }

    @RequestMapping(value = "/password/change.do", method = GET)
    public String changePassword() {
        logger.debug("Rendering change password form.");
        return "change-password";
    }

    @RequestMapping(value = "/password/change.do", method = POST)
    public String processSubmit(HttpServletRequest request,
                                @ModelAttribute("form") ChangePasswordForm form,
                                BindingResult result) {
        logger.debug("Processing change password form.");
        User user = service.findByUsername(getCurrentUsername());
        new ChangePasswordFormValidator(user).validate(form, result);
        if (!result.hasErrors()) {
            user.setPassword(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt()));
            service.update(user);
            request.getSession().setAttribute("message", "You have successfully changed your password.");
            return "redirect:/home.do";
        }
        return "change-password";
    }

    private String getCurrentUsername() {
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return obj instanceof UserDetails ? ((UserDetails) obj).getUsername() : obj.toString();
    }
}
