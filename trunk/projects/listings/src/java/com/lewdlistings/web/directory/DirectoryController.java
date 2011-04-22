package com.lewdlistings.web.directory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/directory")
public class DirectoryController {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryController.class);

    @RequestMapping(method = GET)
    public String directory() {
        logger.debug("Rendering directory index page.");
        return "directory/index";
    }
}