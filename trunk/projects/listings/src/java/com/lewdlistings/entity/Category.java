package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

@Entity
@Table(name = "categories")
@AttributeOverride(name = "id", column = @Column(name = "category_id"))
public class Category extends BaseEntity {

    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    @Column(name = "slug", length = 200, nullable = false, unique = true)
    // The canonical representation of the name. eg. rainy-day for Rainy Day
    private String slug;

    @Column(name = "num_profiles")
    private Long numProfiles;

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private Category parent;

    @OneToMany
    @JoinColumn(name = "parent_id")
    @ForeignKey(name = "fk_parent_id")
    @OrderBy("name")
    private Set<Category> children;

    @ManyToMany(mappedBy = "categories")
    @OrderBy("name")
    private transient List<Post> posts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Long getNumProfiles() {
        return numProfiles;
    }

    public void setNumProfiles(Long numProfiles) {
        this.numProfiles = numProfiles;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(getId())
                .append(getSlug())
                .toString();
    }

    public boolean equals(Category category) {
        return reflectionEquals(this, category);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(17, 31, this);
    }

    public int compareTo(Category category) {
        return reflectionCompare(this, category);
    }
}