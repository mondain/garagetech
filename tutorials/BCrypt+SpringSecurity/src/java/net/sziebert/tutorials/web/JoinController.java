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
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JoinController {

    private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

    private final UserService service;

    @Autowired
    public JoinController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/join.do", method = RequestMethod.GET)
    public String join() {
        logger.debug("Rendering join form.");
        return "join";
    }

    @RequestMapping(value = "/join.do", method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("form") JoinForm form, BindingResult result) {
        logger.debug("Processing join form.");
        new JoinFormValidator().validate(form, result);
        if (!result.hasErrors()) {
            User user = new User(
                    form.getUsername(),
                    BCrypt.hashpw(form.getPassword(), BCrypt.gensalt()),
                    form.getEmail());
            service.create(user);
            return "join-success";
        }
        return "join";
    }

    @ModelAttribute("form")
    public JoinForm populateForm() {
        return new JoinForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields(new String[]{"email", "password", "confirm", "username", "agreeToTerms"});
    }
}