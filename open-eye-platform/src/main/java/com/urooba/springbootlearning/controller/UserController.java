package com.urooba.springbootlearning.controller;


import com.urooba.springbootlearning.entity.Post;
import com.urooba.springbootlearning.entity.User;
import com.urooba.springbootlearning.exception.UserNotFoundException;
import com.urooba.springbootlearning.repository.PostRepository;
import com.urooba.springbootlearning.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class UserController {

    // initializing the variables first and then make the constructor out of it

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    //get all the users
    @GetMapping("/openEye/allUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping("openEye/{id}/viewUser")
    public EntityModel<User> getUserById(@PathVariable final int id) {

        // Fetch user by ID
        Optional<User> optionalUser = userRepository.findById(id);

        // If user not found, throw exception
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("id " + id);
        }

        // Get the actual user object
        User user = optionalUser.get();

        // Wrap user in EntityModel
        EntityModel<User> entityModel = EntityModel.of(user);
        // Create EntityModel and add HATEOAS link
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("allUser"));

        return entityModel;
    }


    @PostMapping("openEye/addUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User newUser) {
        // Save the new user to the database
        User savedUser = userRepository.save(newUser);

        // Build the URI for the newly created user

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        // Return 201 Created with the Location header and the saved user object in the response body
        return ResponseEntity.created(location).body(savedUser);
    }

    @PutMapping("openEye/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody User updatedUser) {
        // Check if the user with the given ID exists
        Optional<User> existingUserOpt = userRepository.findById(id);

        if (!existingUserOpt.isPresent()) {
            return ResponseEntity.notFound().build();  // Return 404 if the user doesn't exist
        }

        // If the user exists, update the fields
        User existingUser = existingUserOpt.get();

        // Update user fields (You can set only the fields you want to update)
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setBirthdate(updatedUser.getBirthdate());
        existingUser.setUserRole(updatedUser.getUserRole());
        existingUser.setPassword(updatedUser.getPassword());

        // Save the updated user to the database
        User savedUser = userRepository.save(existingUser);

        // Return 200 OK with the updated user object
        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("/openEye/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }


    //---------------------------------POSTS REQUESTS-------------------------//

    @GetMapping("/openEye/allPosts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("openEye/{id}/viewPost")
    public EntityModel<Post> getPost(@PathVariable final int id) {

        // Fetch post by ID
        Optional<Post> optionalPost = postRepository.findById(id);


        // If user not found, throw exception
        if (optionalPost.isEmpty()) {
            throw new UserNotFoundException("id " + id);
        }

        // Get the actual post object
        Post post = optionalPost.get();

        // Wrap user in EntityModel
        EntityModel<Post> entityModel = EntityModel.of(post);
        // Create EntityModel and add HATEOAS link
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllPosts());
        entityModel.add(link.withRel("allPost"));

        return entityModel;

    }

    @PostMapping("/openEye/createPost/{id}")
    public ResponseEntity<Post> createPostForUser(@PathVariable final int id, @Valid @RequestBody Post post) {
        // first find the user by id
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException(" id" + id);
        }

        post.setUser(user.get());

        Post postSaved = postRepository.save(post);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(postSaved.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/openEye/editPost/{id}")
    public ResponseEntity<Post> editPost(@PathVariable final int id, @Valid @RequestBody Post editedPost) {
        Optional<Post> prevPost = postRepository.findById(id);

        if (prevPost.isEmpty()) {
            throw new UserNotFoundException("not exist" + id);
        }

        Post existingPost = prevPost.get();

        User user = existingPost.getUser();

        existingPost.setDescription(editedPost.getDescription());

        Post postSaved = postRepository.save(existingPost);

        return ResponseEntity.ok(postSaved);

    }

    @DeleteMapping("/openEye/deletePost/{id}")
    public void deletePost(@PathVariable final int id) {
        postRepository.deleteById(id);

    }


}
