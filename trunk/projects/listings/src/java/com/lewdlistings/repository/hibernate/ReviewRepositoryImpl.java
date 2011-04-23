package com.lewdlistings.repository.hibernate;

import com.lewdlistings.entity.Review;
import com.lewdlistings.repository.ReviewRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepositoryImpl extends GenericRepositoryImpl<Review, Long> implements ReviewRepository {

    private static final Logger logger = LoggerFactory.getLogger(ReviewRepositoryImpl.class);

    @Override
    public List<Review> listRecent(int start, int limit) {
        logger.debug("Listing recent reviews");
        Criteria criteria = criteria();
        criteria.addOrder(Order.desc("created"));
        if (start > 0 && limit > 0) {
            criteria.setFirstResult(start);
            criteria.setMaxResults(limit);
        }
        return list(criteria);
    }
}
