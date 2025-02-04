package com.urooba.springbootlearning.controller;

import com.urooba.springbootlearning.repository.UserRepository;
import com.urooba.springbootlearning.repository.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (user.getPassword() == null) {
            return "Password cannot be null";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");  // Default role
        userRepository.save(user);
        return "User registered successfully";
    }


    //for carrying out the authentication process , we needed the authentication manager bean

    @PostMapping("/logIn")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return new ResponseEntity<>("Username or Password cannot be null", HttpStatus.BAD_REQUEST);
        }

        try {
            // Authenticating the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            // Setting authentication in SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}


