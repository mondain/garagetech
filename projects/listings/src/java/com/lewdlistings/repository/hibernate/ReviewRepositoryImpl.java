package com.lewdlistings.repository.hibernate;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.Review;
import com.lewdlistings.repository.ReviewRepository;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.criterion.Order.desc;
import static org.hibernate.criterion.Restrictions.eq;

@Repository
public class ReviewRepositoryImpl extends GenericRepositoryImpl<Review, Long> implements ReviewRepository {

    private static final Logger logger = LoggerFactory.getLogger(ReviewRepositoryImpl.class);

    public Review findByGuid(String guid) {
        logger.debug("Finding review by guid: {}", guid);
        Criteria criteria = criteria();
        criteria.add(eq("guid", guid));
        return uniqueResult(criteria);
    }

    public List<Review> listForPost(Post post, int start, int limit) {
        logger.debug("Listing reviews for post: {}", post.getGuid());
        Criteria criteria = criteria();
        criteria.add(eq("post", post));
        criteria.addOrder(desc("created"));
        if (start > 0 && limit > 0) {
            criteria.setFirstResult(start);
            criteria.setMaxResults(limit);
        }
        return list(criteria);
    }

    @Override
    public List<Review> listRecent(int start, int limit) {
        logger.debug("Listing recent reviews");
        Criteria criteria = criteria();
        criteria.addOrder(desc("created"));
        if (start > 0 && limit > 0) {
            criteria.setFirstResult(start);
            criteria.setMaxResults(limit);
        }
        return list(criteria);
    }
}
