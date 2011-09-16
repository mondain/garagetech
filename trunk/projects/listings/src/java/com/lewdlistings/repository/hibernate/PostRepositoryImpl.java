package com.lewdlistings.repository.hibernate;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.User;
import com.lewdlistings.repository.PostRepository;
import org.hibernate.Criteria;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.criterion.Order.desc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.gt;

@Repository
public class PostRepositoryImpl extends GenericRepositoryImpl<Post, Long> implements PostRepository {

    private static final Logger logger = LoggerFactory.getLogger(PostRepositoryImpl.class);

    public Post findByGuid(String guid) {
        logger.debug("Finding post by guid: {}", guid);
        Criteria criteria = criteria();
        criteria.add(eq("guid", guid));
        return uniqueResult(criteria);
    }

    public List<Post> listActive() {
        logger.debug("Listing all active posts");
        Criteria criteria = criteria();
        criteria.add(gt("expires", new DateTime()));
        criteria.add(eq("status", Post.Status.ACTIVE));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(desc("averageRating"));
        criteria.addOrder(desc("numReviews"));
        criteria.addOrder(desc("created"));
        return list(criteria);
    }

    public List<Post> listFeatured() {
        logger.debug("Listing all featured posts.");
        Criteria criteria = criteria();
        criteria.add(gt("expires", new DateTime()));
        criteria.add(eq("type", Post.Type.FEATURED));
        criteria.add(eq("status", Post.Status.ACTIVE));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(desc("created"));
        return list(criteria);
    }

    public List<Post> listForUser(User user) {
        logger.debug("Listing all posts for user: {}", user.getUsername());
        return list(criteria().add(eq("author", user)).addOrder(desc("created")));
    }

    public List<Post> listRecent(int start, int limit) {
        logger.debug("Listing all recent posts.");
        Criteria criteria = criteria();
        criteria.add(gt("expires", new DateTime()));
        criteria.add(eq("status", Post.Status.ACTIVE));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.addOrder(desc("created"));
        if (start > 0 && limit > 0) {
            criteria.setFirstResult(start);
            criteria.setMaxResults(limit);
        }
        return list(criteria);
    }
}