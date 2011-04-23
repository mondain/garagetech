package com.lewdlistings.web.review;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.Review;
import com.lewdlistings.io.Consumer;
import com.lewdlistings.io.Producer;

import java.io.Serializable;

public class ReviewForm implements Consumer<Review>, Producer<Review>, Serializable {

    private static final long serialVersionUID = 687670214894351278L;

    private Long reviewId;
    private Post post;
    private double rating;
    private String content;

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void consume(Review review) {
        setReviewId(review.getId());
        setPost(review.getPost());
        setRating(review.getRating());
        setContent(review.getContent());
    }

    @Override
    public void produce(Review review) {
        review.setId(getReviewId());
        review.setPost(getPost());
        review.setRating(getRating());
        review.setContent(getContent());
    }
}
