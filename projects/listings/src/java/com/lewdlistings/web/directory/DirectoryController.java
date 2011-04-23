package com.lewdlistings.web.directory;

import com.lewdlistings.entity.Post;
import com.lewdlistings.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class DirectoryController {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryController.class);

    private final PostService postService;

    @Autowired
    public DirectoryController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/directory", method = GET)
    public String directory(Model model) {
        logger.debug("Rendering directory index page.");
        List<Post> posts = postService.listActive();
        model.addAttribute("posts", posts);
        return "directory/index";
    }
}