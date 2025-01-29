package com.urooba.springbootlearning.repository;

import com.urooba.springbootlearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
