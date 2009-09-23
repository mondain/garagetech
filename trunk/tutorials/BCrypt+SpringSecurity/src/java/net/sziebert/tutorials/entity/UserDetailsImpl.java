/*
 * Copyright (c) 2009 Carl Sziebert
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * $Id$
 */
package net.sziebert.tutorials.entity;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>UserDetailsImpl</code> is a simple <code>UserDetails</code> implementation
 * for Spring Security and provides a lightweight facade around our system
 * <code>User</code> object.
 */
public class UserDetailsImpl implements Serializable, UserDetails {

    private static final long serialVersionUID = 2755218805643703788L;

    private final String username;
    private final String password;
    private final GrantedAuthority[] authorities;

    public UserDetailsImpl(final User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new GrantedAuthorityImpl("ROLE_USER"));
		this.authorities = auth.toArray(new GrantedAuthority[auth.size()]);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public GrantedAuthority[] getAuthorities() {
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