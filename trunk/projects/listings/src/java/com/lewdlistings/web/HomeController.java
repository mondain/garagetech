package com.lewdlistings.web;

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
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/", method = GET)
    public String home(Model model) {
        logger.debug("Rendering home page.");
        addFeaturedPosts(model);
        addPosts(model);
        return "home";
    }

    private void addFeaturedPosts(Model model) {
        List<Post> featured = postService.listFeatured();
        model.addAttribute("featured", featured);
    }

    private void addPosts(Model model) {
        List<Post> posts = postService.listRecent(0, 100);
        model.addAttribute("posts", posts);
    }
}