package com.lewdlistings.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public String login() {
        logger.debug("Rendering login form.");
        return "login";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("j_username", "j_password", "j_persist");
    }
}