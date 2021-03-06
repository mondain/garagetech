package com.lewdlistings.repository;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.User;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {

    Post findByGuid(String guid);

    List<Post> listActive();

    List<Post> listFeatured();

    List<Post> listForUser(User user);

    List<Post> listRecent(int start, int limit);
}
