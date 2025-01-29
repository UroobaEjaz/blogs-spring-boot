package com.urooba.springbootlearning.controller;

import com.urooba.springbootlearning.entity.Post;
import com.urooba.springbootlearning.entity.User;
import com.urooba.springbootlearning.exception.UserNotFoundException;
import com.urooba.springbootlearning.service.PostService;
import com.urooba.springbootlearning.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PostController {

 private PostService postService;


 // generating the constructor; constructor injection
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;

    }

    @GetMapping("/allPosts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("{id}/viewPost")
    public EntityModel<Post> getPost(@PathVariable final int id) {
        Post post = postService.getPost(id);
        // Wrap user in EntityModel
        EntityModel<Post> entityModel = EntityModel.of(post);
        // Create EntityModel and add HATEOAS link
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllPosts());
        entityModel.add(link.withRel("allPost"));

        return entityModel;

    }

    @PostMapping("/createPost/{id}")
    public ResponseEntity<Post> createPostForUser(@PathVariable final int id, @Valid @RequestBody Post post) {

        Post newPost = postService.createPostForUser(id, post);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/editPost/{id}")
    public ResponseEntity<Post> editPost(@PathVariable final int id, @Valid @RequestBody Post editedPost) {
       Post updatedPost = postService.updatingPost(id,editedPost);

        return ResponseEntity.ok(updatedPost);

    }

    @DeleteMapping("deletePost/{id}")
    public void deletePost(@PathVariable final int id) {
      postService.deletePost(id);

    }


}


