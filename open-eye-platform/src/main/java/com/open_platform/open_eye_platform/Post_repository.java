package com.open_platform.open_eye_platform;

import com.open_platform.open_eye_platform.Posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Post_repository extends JpaRepository<Post, Integer> {
}
