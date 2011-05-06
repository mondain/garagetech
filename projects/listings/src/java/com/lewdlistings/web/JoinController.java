package com.lewdlistings.web;

import com.lewdlistings.entity.User;
import com.lewdlistings.flash.FlashMap;
import com.lewdlistings.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/join")
public class JoinController {

    private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

    private final PasswordEncoder passwordEncoder;
    private final UserService service;

    @Autowired
    public JoinController(PasswordEncoder passwordEncoder, UserService service) {
        this.passwordEncoder = passwordEncoder;
        this.service = service;
    }

    @RequestMapping(method = GET)
    public String join() {
        logger.debug("Rendering join form.");
        return "join";
    }

    @RequestMapping(method = POST)
    public String persist(@ModelAttribute("joinForm") JoinForm form, BindingResult result) {
        logger.debug("Processing join form.");
        new JoinFormValidator().validate(form, result);
        if (!result.hasErrors()) {
            User user = new User(
                    form.getUsername(),
                    passwordEncoder.encodePassword(form.getPassword(), null),
                    form.getEmail());
            service.create(user);
            String message = "You have successfully joined Listings.";
            FlashMap.setSuccessMessage(message);
            return "redirect:/";
        }
        return "join";
    }

    @ModelAttribute("joinForm")
    public JoinForm populateForm() {
        return new JoinForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("email", "password", "confirm", "username", "agreeToTerms");
    }
}