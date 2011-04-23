package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends BaseEntity implements Comparable<User> {

    @Column(name = "username", length = 32, nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_role_assn",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @ForeignKey(name = "fk_user_id", inverseName = "fk_role_id")
    @OrderBy("description")
    private List<Role> roles;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append(getUsername())
            .toString();
    }

    public boolean equals(User user) {
        return reflectionEquals(this, user);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(17, 31, this);
    }

    public int compareTo(User user) {
        return reflectionCompare(this, user);
    }
}
