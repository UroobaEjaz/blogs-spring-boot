package com.open_platform.open_eye_platform;

import com.open_platform.open_eye_platform.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_repository extends JpaRepository<User, Integer> {

}
