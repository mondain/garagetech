package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Parent;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

@Embeddable
@Table(name = "post_links")
public class PostLink implements Serializable, Comparable<PostLink> {

    @Parent
    private Post post;

    @Column(name = "alias", length = 512, nullable = true)
    private String alias;

    @Column(name = "url", length = 1024, nullable = false)
    private String url;

    public PostLink() {}

    public PostLink(String url) {
        this(null, url);
    }

    public PostLink(String alias, String url) {
        this.alias = alias;
        this.url = url;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(17, 31, this);
    }

    public boolean equals(PostLink link) {
        return reflectionEquals(this, link);
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        if (getPost() != null && getPost().getId() != null) {
            builder.append(getPost().getId());
        }
        return builder.append(getUrl()).toString();
    }

    @Override
    public int compareTo(PostLink link) {
        return reflectionCompare(this, link);
    }
}
