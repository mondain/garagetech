package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Comparator;

import static org.apache.commons.lang.StringUtils.trimToEmpty;

@Entity
@org.hibernate.annotations.Entity(mutable = false)
@Table(name = "roles")
public class Role implements Serializable, Comparable<Role> {

    private static final long serialVersionUID = -828314946477973093L;

    @Id
    @GeneratedValue
    @Column(name = "role_id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false, length = 30)
    private String name;

    @Column(name = "description", nullable = true, length = 255)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append(getName())
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Role)) return false;
        Role role = (Role) obj;
        // Two roles are equal if and only if their names match.
        String name1 = trimToEmpty(getName());
        String name2 = trimToEmpty(role.getName());
        return name1.equals(name2);
    }

    @Override
    public int hashCode() {
        return trimToEmpty(getName()).hashCode();
    }

    public int compareTo(Role o) {
        return NAME_COMPARATOR.compare(this, o);
    }

    /**
     * Role Comparator (by name).
     */
    public static final Comparator<Role> NAME_COMPARATOR = new Comparator<Role>() {
        public int compare(Role r1, Role r2) {
            String s1 = trimToEmpty(r1.getName());
            String s2 = trimToEmpty(r2.getName());
            return s1.compareTo(s2);
        }
    };

    /**
     * Role Comparator (by description).
     */
    public static final Comparator<Role> DESCRIPTION_COMPARATOR = new Comparator<Role>() {
        public int compare(Role r1, Role r2) {
            String s1 = trimToEmpty(r1.getDescription());
            String s2 = trimToEmpty(r2.getDescription());
            return s1.compareTo(s2);
        }
    };
}