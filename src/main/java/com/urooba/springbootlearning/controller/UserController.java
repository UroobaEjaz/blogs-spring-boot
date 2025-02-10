package com.urooba.springbootlearning.controller;

import com.urooba.springbootlearning.repository.entity.User;
import com.urooba.springbootlearning.service.UserService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}/viewUser")
        public EntityModel<User> getUserById(@PathVariable final int id) {
        User user = userService.getUserById(id);

        // Wrap user in EntityModel
        EntityModel<User> entityModel = EntityModel.of(user);
        // Create EntityModel and add HATEOAS link
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("allUser"));

        return entityModel;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User newUser) {
        // Save the new user to the database
        User savedUser = userService.createUser(newUser);

        // Build the URI for the newly created user

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        // Return 201 Created with the Location header and the saved user object in the response body
        return ResponseEntity.created(location).body(savedUser);
    }


    @PutMapping("/updateUser/{id}")

    public ResponseEntity<User> updateUser(@PathVariable int id, @Valid @RequestBody User updatedUser) {
        User editedUser = userService.updateUser(id, updatedUser);
        // Return 200 OK with the updated user object
        return ResponseEntity.ok(editedUser);
    }

    @DeleteMapping("/delete/{id}")

    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}