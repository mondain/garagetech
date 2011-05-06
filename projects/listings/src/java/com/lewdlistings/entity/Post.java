package com.lewdlistings.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.ForeignKey;
import org.joda.time.DateTime;

import javax.persistence.*;

import java.util.List;

import static org.apache.commons.lang.builder.CompareToBuilder.reflectionCompare;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;

@Entity
@Table(name = "posts")
@AttributeOverride(name = "id", column = @Column(name = "post_id"))
public class Post extends BaseEntity implements Comparable<Post> {

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ForeignKey(name = "fk_author_id")
    private User author;

    @Column(name = "guid", length = 256, nullable = false, unique = true)
    private String guid;

    @Column(name = "display_name", length = 256)
    private String displayName;

    @Column(name = "summary", nullable = false, columnDefinition = "text")
    private String summary;

    @Column(name = "content", nullable = false, columnDefinition = "longtext")
    private String content;

    @Column(name = "phone", length = 16)
    @org.hibernate.annotations.Type(type = "phone")
    private PhoneNumber phone;

    @Column(name = "location", length = 200)
    private String location;

    @Column(name = "avg_rating")
    private double averageRating;

    @Column(name = "num_reviews")
    private int numReviews;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20, nullable = false)
    private Type type;

    @Column(name = "expires_at")
    @org.hibernate.annotations.Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime expires;

    @ElementCollection
    @CollectionTable(
            name = "post_attributes",
            joinColumns = @JoinColumn(name = "post_id")
    )
    @OrderBy("name")
    private List<PostAttribute> attributes;

    @OneToMany
    @JoinColumn(name = "post_id")
    @OrderBy("created")
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "post_tag_assn",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ForeignKey(name = "fk_post_tag_id", inverseName = "fk_tag_post_id")
    @OrderBy("name")
    private List<Tag> tags;

    public enum Status {
        ACTIVE, ARCHIVED, DRAFT, FLAGGED, SUSPENDED
    }

    public enum Type {
        AD_HOC, BASIC, BUMP, FEATURED
    }

    public Post() {
        this.status = Status.DRAFT;
        this.type = Type.BASIC;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PhoneNumber getPhone() {
        return phone;
    }

    public void setPhone(PhoneNumber phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public DateTime getExpires() {
        return expires;
    }

    public void setExpires(DateTime expires) {
        this.expires = expires;
    }

    public List<PostAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<PostAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(getId())
                .toString();
    }

    public boolean equals(Post post) {
        return reflectionEquals(this, post);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(17, 31, this);
    }

    public int compareTo(Post post) {
        return reflectionCompare(this, post);
    }
}
