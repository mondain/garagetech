package com.lewdlistings.repository;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.Review;

import java.util.List;

public interface ReviewRepository extends GenericRepository<Review, Long> {

    Review findByGuid(String guid);

    List<Review> listForPost(Post post, int start, int limit);

    List<Review> listRecent(int start, int limit);
}
