package com.lewdlistings.web.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    public ReviewController() {

    }

    @RequestMapping(value = "/reviews", method = GET)
    public String list(Model model) {
        logger.debug("Listing recent reviews");

        return "review/list";
    }
}
