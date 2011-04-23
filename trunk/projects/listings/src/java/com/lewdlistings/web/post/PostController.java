package com.lewdlistings.web.post;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.PostAttribute;
import com.lewdlistings.entity.PostAttributes;
import com.lewdlistings.entity.User;
import com.lewdlistings.flash.FlashMap;
import com.lewdlistings.service.PostService;
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

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/posts", method = GET)
    public String list(Model model) {
        User user = UserThreadLocal.getCurrentUser();
        logger.debug("Listing all posts for user: {}", user.getUsername());
        model.addAttribute("posts", postService.listForUser(user));
        return "post/list";
    }

    @RequestMapping(value = "/post/{postId}", method = GET)
    public String find(@PathVariable("postId") Long postId, Model model) {
        logger.debug("Displaying post for id: {}", postId);
        Post post = postService.read(postId);
        model.addAttribute("post", post);
        return "post/index";
    }

    @RequestMapping(value = "/post/{postId}/edit", method = GET)
    public String edit(@PathVariable("postId") Long postId, Model model) {
        logger.debug("Editing post with id: {}", postId);
        Post post = postService.read(postId);
        PostForm form = new PostForm();
        form.consume(post);
        model.addAttribute("editPostForm", form);
        model.addAttribute("isEdit", true);
        return "post/edit";
    }

    @RequestMapping(value = "/post/new", method = GET)
    public String types(Model model) {
        logger.debug("Listing post types");
        model.addAttribute("types", Post.Type.values());
        return "post/types";
    }

    @RequestMapping(value = "/post/new/{type}", method = GET)
    public String create(@PathVariable("type") String type, Model model) {
        logger.debug("Creating new post");
        PostForm form = defaultForm();
        try {
            form.setType(Post.Type.valueOf(type.toUpperCase()));
            model.addAttribute("editPostForm", form);
            model.addAttribute("isEdit", false);
            return "post/edit";
        } catch (IllegalArgumentException e) {
            FlashMap.setWarningMessage("We don't know what ad type you wanted. Choose from the selection below.");
            return "redirect:/posts/types";
        }
    }

    // TODO: Remove this method
    @RequestMapping(value = "/post/{postId}/bmp", method = GET)
    public String bump(@PathVariable("postId") Long postId) {
        logger.debug("Bumping post with id: {}", postId);
        Post post = postService.read(postId);
        // TODO: Make sure this doesn't overwrite up negative types
        post.setStatus(Post.Status.ACTIVE);
        postService.persist(post);
        FlashMap.setSuccessMessage("Your post has been successfully bumped.");
        return "redirect:/posts";
    }

    // TODO: This should be a delete or post
    @RequestMapping(value = "/post/{postId}/delete", method = POST)
    public String delete(@PathVariable("postId") Long postId) {
        logger.debug("Deleting post with id: {}", postId);
        FlashMap.setSuccessMessage("Your post has been successfully deleted.");
        return "redirect:/posts";
    }

    @RequestMapping(method = POST)
    public String persist(Model model, @ModelAttribute("editPostForm") @Valid PostForm form, BindingResult result) {
        logger.debug("Saving post changes");
        if (!result.hasErrors()) {
            Post post = form.getPostId() != null ? postService.read(form.getPostId()) : new Post();
            form.produce(post);
            if (post.getAuthor() == null) {
                post.setAuthor(UserThreadLocal.getCurrentUser());
            }
            if (post.getExpires() == null) {
                post.setExpires(new DateTime().plusDays(30));
            }
            post.setStatus(Post.Status.ACTIVE);
            postService.persist(post);
            FlashMap.setSuccessMessage("Your post has been successfully saved.");
            return "redirect:/posts";
        }
        model.addAttribute("isEdit", form.getPostId() != null);
        return "post/edit";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Post.Type.class, new EnumPropertyEditor(Post.Type.class));
        //binder.setAllowedFields("postId", "summary", "content", "type", "hiddenAction", "phone", "location");
    }

    private PostForm defaultForm() {
        PostForm form = new PostForm();
        List<PostAttribute> attributes = form.getAttributes();
        for (PostAttributes attr : PostAttributes.values()) {
            attributes.add(new PostAttribute(attr.name()));
        }
        form.setAttributes(attributes);
        return form;
    }
}
