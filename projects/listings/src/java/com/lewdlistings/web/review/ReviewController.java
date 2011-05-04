package com.lewdlistings.web.review;

import com.lewdlistings.entity.Review;
import com.lewdlistings.flash.FlashMap;
import com.lewdlistings.service.PostService;
import com.lewdlistings.service.ReviewService;
import com.lewdlistings.util.RandomStringGenerator;
import com.lewdlistings.web.util.UserThreadLocal;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ReviewController {

    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    private final PostService postService;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(PostService postService, ReviewService reviewService) {
        this.postService = postService;
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "/review/new", method = GET)
    public String create(@RequestParam(value = "post", required = false) String postGuid, Model model) {
        logger.debug("Creating new review");
        ReviewForm form = new ReviewForm();
        // TODO: Figure out the proper set of defaults for a new review
        form.setPostGuid(postGuid);
        model.addAttribute("editReviewForm", form);
        model.addAttribute("isEdit", false);
        return "review/edit";
    }

    @RequestMapping(value = "/review/{guid}", method = GET)
    public String find(@PathVariable("guid") String guid, Model model) {
        logger.debug("Displaying review for guid: {}", guid);
        Review review = reviewService.findByGuid(guid);
        model.addAttribute("review", review);
        return "review/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("guid", "postGuid", "rating", "content");
    }

    @RequestMapping(value = "/reviews", method = GET)
    public String list(Model model) {
        logger.debug("Listing recent reviews");
        List<Review> reviews = reviewService.listRecent(0, 0);
        model.addAttribute("reviews", reviews);
        return "review/list";
    }

    @RequestMapping(method = POST)
    public String persist(@ModelAttribute("editReviewForm") @Valid ReviewForm form, BindingResult result, Model model) {
        logger.debug("Saving review changes");
        if (!result.hasErrors()) {
            Review review = isNotBlank(form.getGuid()) ? reviewService.findByGuid(form.getGuid()) : new Review();
            form.produce(review);
            if (form.getPostGuid() != null) {
                review.setPost(postService.findByGuid(form.getPostGuid()));
            }
            if (review.getAuthor() == null) {
                review.setAuthor(UserThreadLocal.getCurrentUser());
            }
            if (StringUtils.isBlank(review.getGuid())) {
                review.setGuid(RandomStringGenerator.getNextRandomString());
            }
            reviewService.persist(review);
            FlashMap.setSuccessMessage("Your review has been successfully saved.");
            return "redirect:/review/" + review.getGuid();
        }
        model.addAttribute("isEdit", isNotBlank(form.getGuid()));
        return "review/edit";
    }
}
