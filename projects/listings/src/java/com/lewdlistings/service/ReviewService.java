package com.lewdlistings.service;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.Review;

import java.util.List;

public interface ReviewService {

    Review findByGuid(String guid);

    List<Review> listForPost(Post post, int start, int limit);

    List<Review> listRecent(int start, int limit);

    void persist(Review review);

    Review read(Long id);
}
