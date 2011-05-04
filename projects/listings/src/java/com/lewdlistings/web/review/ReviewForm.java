package com.lewdlistings.web.review;

import com.lewdlistings.entity.Review;
import com.lewdlistings.io.Consumer;
import com.lewdlistings.io.Producer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ReviewForm implements Consumer<Review>, Producer<Review>, Serializable {

    private static final long serialVersionUID = 687670214894351278L;

    private String guid;

    @NotNull
    private String postGuid;

    @NotNull
    private Double rating;

    @NotNull
    @Size(min = 1, max = 2000, message = "error.content.min.max")
    private String content;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPostGuid() {
        return postGuid;
    }

    public void setPostGuid(String postGuid) {
        this.postGuid = postGuid;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
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
        setGuid(review.getGuid());
        setRating(review.getRating());
        setContent(review.getContent());
    }

    @Override
    public void produce(Review review) {
        review.setGuid(getGuid());
        review.setRating(getRating());
        review.setContent(getContent());
    }
}
