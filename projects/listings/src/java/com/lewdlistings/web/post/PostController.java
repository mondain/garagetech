package com.lewdlistings.web.post;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.Review;
import com.lewdlistings.entity.User;
import com.lewdlistings.flash.FlashMap;
import com.lewdlistings.service.PostService;
import com.lewdlistings.service.ReviewService;
import com.lewdlistings.util.RandomStringGenerator;
import com.lewdlistings.web.propertyeditor.EnumPropertyEditor;
import com.lewdlistings.web.util.UserThreadLocal;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;
    private final ReviewService reviewService;

    @Autowired
    public PostController(PostService postService, ReviewService reviewService) {
        this.postService = postService;
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "/ads", method = GET)
    public String list(Model model) {
        User user = UserThreadLocal.getCurrentUser();
        logger.debug("Listing all posts for user: {}", user.getUsername());
        model.addAttribute("posts", postService.listForUser(user));
        return "post/list";
    }

    @RequestMapping(value = "/ad/{guid}", method = GET)
    public String find(@PathVariable("guid") String guid, Model model) {
        logger.debug("Displaying post for guid: {}", guid);
        Post post = postService.findByGuid(guid);
        model.addAttribute("post", post);
        return "post/index";
    }

    @RequestMapping(value = "/ad/{guid}/edit", method = GET)
    public String edit(@PathVariable("guid") String guid, Model model) {
        logger.debug("Editing post with guid: {}", guid);
        Post post = postService.findByGuid(guid);
        PostForm form = PostForm.defaultForm();
        form.consume(post);
        model.addAttribute("editPostForm", form);
        model.addAttribute("isEdit", true);
        return "post/edit";
    }

    @RequestMapping(value = "/ad/new", method = GET)
    public String types(Model model) {
        logger.debug("Listing post types");
        model.addAttribute("types", Post.Type.values());
        return "post/types";
    }

    @RequestMapping(value = "/ad/new/{type}", method = GET)
    public String create(@PathVariable("type") String type, Model model) {
        logger.debug("Creating new post");
        PostForm form = PostForm.defaultForm();
        try {
            form.setType(Post.Type.valueOf(type.toUpperCase()));
            model.addAttribute("editPostForm", form);
            model.addAttribute("isEdit", false);
            return "post/edit";
        } catch (IllegalArgumentException e) {
            FlashMap.setWarningMessage("We don't know what ad type you wanted. Choose from the selection below.");
            return "redirect:/ads/types";
        }
    }

    // TODO: Remove this method
    @RequestMapping(value = "/ad/{guid}/bmp", method = GET)
    public String bump(@PathVariable("guid") String guid) {
        logger.debug("Bumping post with guid: {}", guid);
        Post post = postService.findByGuid(guid);
        // TODO: Make sure this doesn't overwrite up negative types
        post.setStatus(Post.Status.ACTIVE);
        postService.persist(post);
        FlashMap.setSuccessMessage("Your post has been successfully bumped.");
        return "redirect:/ads";
    }

    // TODO: This should be a delete or post
    @RequestMapping(value = "/ad/{guid}/delete", method = POST)
    public String delete(@PathVariable("guid") String guid) {
        logger.debug("Deleting post with guid: {}", guid);
        FlashMap.setSuccessMessage("Your post has been successfully deleted.");
        return "redirect:/ads";
    }

    @RequestMapping(method = POST)
    public String persist(@ModelAttribute("editPostForm") @Valid PostForm form, BindingResult result, Model model) {
        logger.debug("Saving post changes");
        if (!result.hasErrors()) {
            Post post = isNotBlank(form.getGuid()) ? postService.findByGuid(form.getGuid()) : new Post();
            form.produce(post);
            if (post.getAuthor() == null) {
                post.setAuthor(UserThreadLocal.getCurrentUser());
            }
            if (post.getExpires() == null) {
                post.setExpires(new DateTime().plusDays(30));
            }
            if (post.getGuid() == null) {
                post.setGuid(RandomStringGenerator.getNextRandomString().toLowerCase());
            }
            post.setStatus(Post.Status.ACTIVE);
            postService.persist(post);
            FlashMap.setSuccessMessage("Your post has been successfully saved.");
            return "redirect:/ad/" + post.getGuid();
        }
        model.addAttribute("isEdit", isNotBlank(form.getGuid()));
        return "post/edit";
    }

    @RequestMapping(value = "/ad/{guid}/reviews", method = GET)
    public String reviews(@PathVariable("guid") String guid, Model model) {
        logger.debug("Listing all reviews for post with guid: {}", guid);
        Post post = postService.findByGuid(guid);
        List<Review> reviews = reviewService.listForPost(post, 0, 0);
        model.addAttribute("post", post);
        model.addAttribute("reviews", reviews);
        return "post/reviews";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Post.Type.class, new EnumPropertyEditor(Post.Type.class));
        binder.setAllowedFields("postId", "guid", "displayName", "summary", "content", "phone", "location", "type",
                "tagInput", "attributes[*].name", "attributes[*].stringValue");
    }
}
