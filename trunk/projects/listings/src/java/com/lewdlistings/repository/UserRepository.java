package com.lewdlistings.repository;

import com.lewdlistings.entity.Role;
import com.lewdlistings.entity.User;

import java.util.List;

public interface UserRepository extends GenericRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);
    List<User> listByRole(Role role);
}
