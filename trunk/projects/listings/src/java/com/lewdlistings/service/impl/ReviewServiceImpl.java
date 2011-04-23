package com.lewdlistings.service.impl;

import com.lewdlistings.entity.Review;
import com.lewdlistings.repository.ReviewRepository;
import com.lewdlistings.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("reviewService")
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepos;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepos) {
        this.reviewRepos = reviewRepos;
    }

    @Transactional(readOnly = true)
    public List<Review> listRecent(int start, int limit) {
        logger.debug("Listing recent reviews");
        return reviewRepos.listRecent(start, limit);
    }
}
