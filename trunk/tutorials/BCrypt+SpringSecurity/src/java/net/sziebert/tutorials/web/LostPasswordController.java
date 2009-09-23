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
import net.sziebert.tutorials.service.MailService;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LostPasswordController {

    private static final Logger logger = LoggerFactory.getLogger(LostPasswordController.class);

    private final MailService sender;
    private final UserService service;

    @Autowired
    public LostPasswordController(MailService sender, UserService service) {
        this.sender = sender;
        this.service = service;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(new String[]{"captcha", "username"});
    }

    @ModelAttribute("form")
    public LostPasswordForm populateForm() {
        return new LostPasswordForm();
    }

    @RequestMapping(value = "/password/lost.do", method = GET)
    public String lostPassword() {
        logger.debug("Rendering lost password form.");
        return "lost-password";
    }

    @RequestMapping(value = "/password/lost.do", method = POST)
    public String processSubmit(HttpServletResponse response, @ModelAttribute("form") LostPasswordForm form, BindingResult result) {
        logger.debug("Processing lost password form.");
        new LostPasswordFormValidator().validate(form, result);
        if (!result.hasErrors()) {
            User user = service.findByUsername(form.getUsername());
            if (user != null) {
                String frob = BCrypt.hashpw(user.getUsername() + "3m4il", BCrypt.gensalt());
                String link = createLostPasswordLink(user, frob);
                sender.sendLostPasswordEmail(user, link);
                response.addCookie(persistFrob(frob));
                return "lost-password-success";
            }
            result.rejectValue("username", "error.username.invalid");
        }
        return "lost-password";
    }

    private String createLostPasswordLink(final User user, final String frob) {
        StringBuilder link = new StringBuilder();
        link.append("http://localhost:8080/password/reset.do?frob=");
        link.append(frob);
        link.append("&username=");
        link.append(user.getUsername());
        return link.toString();
    }

    private Cookie persistFrob(final String frob) {
        Cookie cookie = new Cookie("frob", frob);
        cookie.setMaxAge(60 * 60); // 1 hour
        return cookie;
    }
}