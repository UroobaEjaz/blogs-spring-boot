package com.urooba.springbootlearning.repository;

import com.urooba.springbootlearning.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //find by username
    Optional<User> findByUsername(String username);



}
