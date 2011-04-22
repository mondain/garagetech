package com.lewdlistings.repository;

import com.lewdlistings.entity.User;

public interface UserRepository extends GenericRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);
}
