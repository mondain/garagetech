package com.lewdlistings.repository;

import com.lewdlistings.entity.Role;

public interface RoleRepository extends GenericRepository<Role, Long> {

    Role findByName(String name);
}
