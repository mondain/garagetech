package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Parent;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import java.util.Comparator;

import static org.apache.commons.lang.StringUtils.trimToEmpty;
import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

@Embeddable
@Table(name = "post_attributes")
public class PostAttribute extends Attribute implements Comparable<PostAttribute> {

    @Parent
    private Post post;

    public PostAttribute() {
    }

    public PostAttribute(String name) {
        setName(name);
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(17, 31, this);
    }

    public boolean equals(PostAttribute attribute) {
        return reflectionEquals(this, attribute);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(getPost().getId())
                .append(getName())
                .toString();
    }

    @Override
    public int compareTo(PostAttribute attribute) {
        return reflectionCompare(this, attribute);
    }

    /**
     * Tag Comparator (by name).
     */
    public static final Comparator<PostAttribute> NAME_COMPARATOR = new Comparator<PostAttribute>() {

        public int compare(PostAttribute p1, PostAttribute p2) {
            String s1 = trimToEmpty(p1.getName());
            String s2 = trimToEmpty(p2.getName());
            return s1.compareTo(s2);
        }
    };
}
