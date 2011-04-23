package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

@Entity
@Table(name = "reviews")
@AttributeOverride(name = "id", column = @Column(name = "review_id"))
public class Review extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @ForeignKey(name = "fk_review_author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @ForeignKey(name = "fk_review_post_id")
    private Post post;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;

    @Column(name = "promoted")
    private boolean promoted;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPromoted() {
        return promoted;
    }

    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(getId())
                .toString();
    }

    public boolean equals(Review review) {
        return reflectionEquals(this, review);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(17, 31, this);
    }

    public int compareTo(Review review) {
        return reflectionCompare(this, review);
    }
}
