package com.lewdlistings.service.impl;

import com.lewdlistings.entity.Post;
import com.lewdlistings.entity.User;
import com.lewdlistings.repository.PostRepository;
import com.lewdlistings.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepos;

    @Autowired
    public PostServiceImpl(PostRepository postRepos) {
        this.postRepos = postRepos;
    }

    @Transactional(readOnly = true)
    public List<Post> listFeatured() {
        logger.debug("Listing all featured posts.");
        return postRepos.listFeatured();
    }

    @Transactional(readOnly = true)
    public List<Post> listForUser(User user) {
        logger.debug("Listing all posts for user: {}", user.getUsername());
        return postRepos.listForUser(user);
    }

    @Transactional(readOnly = true)
    public List<Post> listRecent(int start, int limit) {
        logger.debug("Listing all recent posts.");
        return postRepos.listRecent(start, limit);
    }
    
    public void persist(Post post) {
        logger.debug("Creating new post");
        postRepos.saveOrUpdate(post);
    }

    @Transactional(readOnly = true)
    public Post read(Long postId) {
        logger.debug("Finding post by id: {}", postId);
        return postRepos.read(postId);
    }
}