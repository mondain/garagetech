package com.lewdlistings.service.impl;

import com.lewdlistings.entity.Role;
import com.lewdlistings.entity.Roles;
import com.lewdlistings.entity.User;
import com.lewdlistings.entity.UserDetailsImpl;
import com.lewdlistings.repository.RoleRepository;
import com.lewdlistings.repository.UserRepository;
import com.lewdlistings.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final RoleRepository roleRepos;
    private final UserRepository userRepos;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepos, UserRepository userRepos) {
        this.roleRepos = roleRepos;
        this.userRepos = userRepos;
    }

    public User create(User user) {
        Role userRole = roleRepos.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();
        roles.add(userRole);
        user.setRoles(roles);
        return userRepos.create(user);
    }

    public void delete(User user) {
        userRepos.delete(user);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        logger.debug("Finding user by email: {}", email);
        return userRepos.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        logger.debug("Finding user by username: {}", username);
        return userRepos.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<User> listActiveProviders() {
        return userRepos.listByRole(roleRepos.findByName(Roles.ROLE_PROVIDER.name()));
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        logger.debug("Loading user by username: {}", username);
        User user = null;
        if (isNotBlank(username)) {
            if (username.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,4}")) {
                user = findByEmail(username);
                if (user == null) {
                    user = findByUsername(username);
                }
            } else {
                user = findByUsername(username);
            }
        }
        if (user == null || user.getPassword() == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        return new UserDetailsImpl(user);
    }

    @Transactional(readOnly = true)
    public User read(Long userId) {
        return userRepos.read(userId);
    }

    public void update(User user) {
        userRepos.update(user);
    }
}