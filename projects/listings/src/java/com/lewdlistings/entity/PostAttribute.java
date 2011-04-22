package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Parent;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

@Embeddable
@Table(name = "post_attributes")
public class PostAttribute extends Attribute implements Comparable<PostAttribute> {

    @Parent
    private Post post;

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
}
