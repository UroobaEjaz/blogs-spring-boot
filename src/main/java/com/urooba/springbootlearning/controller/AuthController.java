package com.urooba.springbootlearning.controller;//package com.urooba.springbootlearning.controller;
//
//import com.urooba.springbootlearning.repository.UserRepository;
//import com.urooba.springbootlearning.repository.entity.User;
//import com.urooba.springbootlearning.security.JwtUtilClass;
//import com.urooba.springbootlearning.service.CustomUserDetailsService;
//import com.urooba.springbootlearning.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
import com.urooba.springbootlearning.repository.UserRepository;
import com.urooba.springbootlearning.repository.entity.User;
import com.urooba.springbootlearning.security.jwt.JwtUtilClass;
import com.urooba.springbootlearning.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
        private UserRepository userRepository;
         private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtilClass jwtUtilClass;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtUtilClass jwtUtilClass) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtilClass = jwtUtilClass;
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(user.getRoles());
        userRepository.save(user);
        return "User registered successfully!";
    }



    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
            // Authenticate the user with username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            // If authentication is successful, generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String token = jwtUtilClass.generateToken(userDetails);

            // Return the generated token
            return ResponseEntity.ok("Bearer: " + token);


    }
}





