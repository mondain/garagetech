package com.lewdlistings.repository.hibernate;

import com.lewdlistings.entity.User;
import com.lewdlistings.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Restrictions.eq;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<User, Long> implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public User findByEmail(String email) {
        logger.debug("Finding user by email: {}", email);
        return uniqueResult(criteria().add(eq("email", email)));
    }

    public User findByUsername(String username) {
        logger.debug("Finding user by username: {}", username);
        return uniqueResult(criteria().add(eq("username", username)));
    }
}