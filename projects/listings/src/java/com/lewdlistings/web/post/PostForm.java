package com.lewdlistings.web.post;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.PostAttribute;
import com.lewdlistings.entity.PostAttributes;
import com.lewdlistings.entity.Tag;
import com.lewdlistings.io.Consumer;
import com.lewdlistings.io.Producer;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.list.LazyList;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostForm implements Consumer<Post>, Producer<Post>, Serializable {

    private static final long serialVersionUID = 1329081043883208387L;

    private Long postId;

    @Size(min = 1, max = 200, message = "error.summary.min.max")
    private String summary;

    @Size(min = 1, max = 2000, message = "error.content.min.max")
    private String content;

    @NotNull
    private String phone;

    @NotNull
    private String location;

    @NotNull
    private Post.Type type;

    private List<Tag> tags;

    @SuppressWarnings({"unchecked"})
    private List<PostAttribute> attributes =
            LazyList.decorate(new ArrayList<PostAttribute>(), new Factory() {
                @Override
                public Object create() {
                    return new PostAttribute();
                }
            });

    public static PostForm defaultForm() {
        PostForm form = new PostForm();
        List<PostAttribute> attributes = form.getAttributes();
        for (PostAttributes attr : PostAttributes.values()) {
            attributes.add(new PostAttribute(attr.name()));
        }
        form.setAttributes(attributes);
        return form;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Post.Type getType() {
        return type;
    }

    public void setType(Post.Type type) {
        this.type = type;
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

    @Override
    public void consume(Post post) {
        setPostId(post.getId());
        setSummary(post.getSummary());
        setContent(post.getContent());
        setType(post.getType());
        setPhone(post.getPhone());
        setLocation(post.getLocation());
        setAttributes(post.getAttributes());
        // TODO: Merge in tags
    }

    @Override
    public void produce(Post post) {
        post.setId(getPostId());
        post.setSummary(getSummary());
        post.setContent(getContent());
        post.setType(getType());
        post.setPhone(getPhone());
        post.setLocation(getLocation());
        post.setAttributes(getAttributes());
    }
}