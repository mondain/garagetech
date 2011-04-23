package com.lewdlistings.repository;

import com.lewdlistings.entity.Review;

import java.util.List;

public interface ReviewRepository extends GenericRepository<Review, Long> {

    List<Review> listRecent(int start, int limit);
}
