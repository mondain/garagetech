package com.lewdlistings.web.account;

import com.lewdlistings.entity.User;
import com.lewdlistings.service.PostService;
import com.lewdlistings.web.util.UserThreadLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final PostService postService;

    @Autowired
    public AccountController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = GET)
    public String account(Model model) {
        User user = UserThreadLocal.getCurrentUser();
        logger.debug("Rendering dashboard for user: {}", user.getUsername());
        model.addAttribute("posts", postService.listForUser(user));
        return "account/index";
    }
}
