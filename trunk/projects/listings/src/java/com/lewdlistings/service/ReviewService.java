package com.lewdlistings.service;

import com.lewdlistings.entity.Review;

import java.util.List;

public interface ReviewService {

    List<Review> listRecent(int start, int limit);
}
