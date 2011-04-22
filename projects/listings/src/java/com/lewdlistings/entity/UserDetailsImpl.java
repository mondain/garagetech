package com.lewdlistings.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements Serializable, UserDetails {

    private static final long serialVersionUID = 2755218805643703788L;

    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> authorities;

    public UserDetailsImpl(final User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new ArrayList<GrantedAuthority>();
        for (Role role : user.getRoles()) {
			this.authorities.add(new GrantedAuthorityImpl(role.getName()));
		}
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}