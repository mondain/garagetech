package com.lewdlistings.service;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.User;

import java.util.List;

public interface PostService {

    List<Post> listActive();

    List<Post> listFeatured();

    List<Post> listForUser(User user);

    List<Post> listRecent(int start, int limit);

    void persist(Post post);

    Post read(Long postId);
}
