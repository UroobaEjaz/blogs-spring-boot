package com.urooba.springbootlearning.service;


import com.urooba.springbootlearning.repository.entity.Post;
import com.urooba.springbootlearning.repository.entity.User;
import com.urooba.springbootlearning.exception.UserNotFoundException;
import com.urooba.springbootlearning.repository.PostRepository;
import com.urooba.springbootlearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    //get post by Id
    public Post getPostById(int id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Post with ID " + id + " not found"));

    }

    public Post createPostForUser(int userId, Post post) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException(" id" + userId);
        }

        post.setUser(user.get());

        return postRepository.save(post);

    }

    public Post updatePost(int id, Post editedPost) {
        Optional<Post> prevPost = postRepository.findById(id);

        if (prevPost.isEmpty()) {
            throw new UserNotFoundException("Invalid post ID: " + id);
        }

        Post existingPost = prevPost.get();
        existingPost.setDescription(editedPost.getDescription());

        return postRepository.save(existingPost);
    }

    public void deletePost(int id) {
        postRepository.deleteById(id);
    }


}
