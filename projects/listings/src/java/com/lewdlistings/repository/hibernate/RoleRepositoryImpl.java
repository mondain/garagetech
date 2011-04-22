package com.lewdlistings.repository.hibernate;

import com.lewdlistings.entity.Role;
import com.lewdlistings.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import static org.hibernate.criterion.Restrictions.eq;

@Repository
public class RoleRepositoryImpl extends GenericRepositoryImpl<Role, Long> implements RoleRepository {

    private static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);

    @Override
    public Role findByName(String name) {
        logger.debug("Finding role by name: {}", name);
        return uniqueResult(criteria().add(eq("name", name)));
    }
}
