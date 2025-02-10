package com.urooba.springbootlearning.service;

import com.urooba.springbootlearning.repository.entity.User;
import com.urooba.springbootlearning.exception.UserNotFoundException;
import com.urooba.springbootlearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // get info for all the users from repo
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //get by the userId
    public User getUserById(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }

    //adding the user
    public User createUser(User user) {
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    //updating the user
    public User updateUser(int id, User updatedUser){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            User existingUser = optionalUser.get();

            // Update user fields (You can set only the fields you want to update)
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setBirthdate(updatedUser.getBirthdate());
            existingUser.setRoles(updatedUser.getRoles());
            existingUser.setPassword(updatedUser.getPassword());
            return userRepository.save(existingUser);
        }
        else {
            throw new UserNotFoundException(id + "not found");
        }

    }

    //deleting the user

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }









}
