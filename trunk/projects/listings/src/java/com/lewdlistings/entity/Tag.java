package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Comparator;
import java.util.List;

import static org.apache.commons.lang.StringUtils.trimToEmpty;
import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

@Entity
@Table(name = "tags")
@AttributeOverride(name = "id", column = @Column(name = "tag_id"))
public class Tag extends BaseEntity implements Comparable<Tag> {

    public Tag() {}

    public Tag(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    @Column(name = "name", unique = true, nullable = false, length = 255)
    private String name;

    @Column(name = "description", nullable = true, columnDefinition = "text")
    private String description;

    // The canonical representation of the name. eg. rainy-day for Rainy Day
    @Column(name = "slug", unique = true, nullable = false, length = 255)
    private String slug;

    @Column(name = "count")
    private int count;

    @ManyToMany(mappedBy = "tags")
    @OrderBy("created DESC")
    private transient List<Post> posts;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
                .append(getName())
                .toString();
    }

    public boolean equals(Tag tag) {
        return reflectionEquals(this, tag);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(17, 31, this);
    }

    public int compareTo(Tag tag) {
        return reflectionCompare(this, tag);
    }

    /**
     * Tag Comparator (by name).
     */
    public static final Comparator<Tag> NAME_COMPARATOR = new Comparator<Tag>() {
        public int compare(Tag t1, Tag t2) {
            String s1 = trimToEmpty(t1.getName());
            String s2 = trimToEmpty(t2.getName());
            return s1.compareTo(s2);
        }
    };

    /**
     * Tag Comparator (by slug).
     */
    public static final Comparator<Tag> SLUG_COMPARATOR = new Comparator<Tag>() {
        public int compare(Tag t1, Tag t2) {
            String s1 = trimToEmpty(t1.getSlug());
            String s2 = trimToEmpty(t2.getSlug());
            return s1.compareTo(s2);
        }
    };
}
