package com.lewdlistings.web.post;

import com.lewdlistings.entity.Post;
import com.lewdlistings.io.Consumer;
import com.lewdlistings.io.Producer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PostForm implements Consumer<Post>, Producer<Post>, Serializable {

    private static final long serialVersionUID = 1329081043883208387L;

    private Long postId;

    @NotNull
    @Size(min = 1, max = 140, message = "error.title.empty")
    private String title;

    @NotNull
    @Size(min = 1, message = "error.content.empty")
    private String content;

    @NotNull
    private String phone;

    @NotNull
    private String location;

    @NotNull
    private Post.Type type;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public void consume(Post post) {
        setPostId(post.getId());
        setTitle(post.getTitle());
        setContent(post.getContent());
        setType(post.getType());
        setPhone(post.getPhone());
        setLocation(post.getLocation());
    }

    @Override
    public void produce(Post post) {
        post.setId(getPostId());
        post.setTitle(getTitle());
        post.setContent(getContent());
        post.setType(getType());
        post.setPhone(getPhone());
        post.setLocation(getLocation());
    }
}