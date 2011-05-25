package com.lewdlistings.web.post;

import com.lewdlistings.entity.Availability;
import com.lewdlistings.entity.PhoneNumber;
import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.PostAttribute;
import com.lewdlistings.entity.PostAttributes;
import com.lewdlistings.entity.Tag;
import com.lewdlistings.io.Consumer;
import com.lewdlistings.io.Producer;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.list.LazyList;
import org.joda.time.DateMidnight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PostForm implements Consumer<Post>, Producer<Post>, Serializable {

    private static final long serialVersionUID = 1329081043883208387L;

    private String guid;
    private String displayName;
    private String summary;
    private String content;
    private PhoneNumber phone;
    private Availability availability;
    private Post.Type type;
    private String tagInput;

    @SuppressWarnings({"unchecked"})
    private List<Tag> tags =
            LazyList.decorate(new ArrayList<Tag>(), new Factory() {
                @Override
                public Object create() {
                    return new Tag();
                }
            });

    @SuppressWarnings({"unchecked"})
    private List<PostAttribute> attributes =
            LazyList.decorate(new ArrayList<PostAttribute>(), new Factory() {
                @Override
                public Object create() {
                    return new PostAttribute();
                }
            });

    @SuppressWarnings({"unchecked"})
    private List<PostAttribute> links =
            LazyList.decorate(new ArrayList<PostAttribute>(), new Factory() {
                @Override
                public Object create() {
                    return new PostAttribute();
                }
            });

    public static PostForm defaultForm() {
        PostForm form = new PostForm();
        Availability current = new Availability(
                new DateMidnight().toDateTime(),
                new DateMidnight().plusDays(3).toDateTime());
        form.setAvailability(current);
        List<PostAttribute> attributes = form.getAttributes();
        for (PostAttributes attr : PostAttributes.values()) {
            attributes.add(new PostAttribute(attr.name(), PostAttribute.Type.DETAIL));
        }
        form.setAttributes(attributes);
        List<PostAttribute> links = form.getLinks();
        links.add(new PostAttribute("Facebook", PostAttribute.Type.LINK));
        links.add(new PostAttribute("Twitter", PostAttribute.Type.LINK));
        links.add(new PostAttribute("Lovings", PostAttribute.Type.LINK));
        links.add(new PostAttribute("Redbook", PostAttribute.Type.LINK));
        form.setLinks(links);
        return form;
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

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Post.Type getType() {
        return type;
    }

    public void setType(Post.Type type) {
        this.type = type;
    }

    public String getTagInput() {
        return tagInput;
    }

    public void setTagInput(String tagInput) {
        this.tagInput = tagInput;
        // TODO: Parse the strings into tags
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<PostAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<PostAttribute> attributes) {
        this.attributes = attributes;
    }

    public void setAttributes(Set<PostAttribute> attributes) {
        if (!this.attributes.isEmpty()) {
            this.attributes.clear();
        }
        this.attributes.addAll(attributes);
    }

    public List<PostAttribute> getLinks() {
        return links;
    }

    public void setLinks(List<PostAttribute> links) {
        this.links = links;
    }

    public void setLinks(Set<PostAttribute> links) {
        if (!this.links.isEmpty()) {
            this.links.clear();
        }
        this.links.addAll(links);
    }

    @Override
    public void consume(Post post) {
        setSummary(post.getSummary());
        setContent(post.getContent());
        setType(post.getType());
        setPhone(post.getPhone());
        setAvailability(post.getAvailability());
        setAttributes(post.getAttributesForType(PostAttribute.Type.DETAIL));
        setLinks(post.getAttributesForType(PostAttribute.Type.LINK));
        setTags(post.getTags());
        setGuid(post.getGuid());
        setDisplayName(post.getDisplayName());
    }

    @Override
    public void produce(Post post) {
        post.setSummary(getSummary());
        post.setContent(getContent());
        post.setType(getType());
        post.setPhone(getPhone());
        post.setAvailability(getAvailability());
        Set<PostAttribute> attrs = new HashSet<PostAttribute>();
        attrs.addAll(getAttributes());
        attrs.addAll(getLinks());
        post.setAttributes(attrs);
        post.setTags(getTags());
        post.setGuid(getGuid());
        post.setDisplayName(getDisplayName());
    }
}