package com.open_platform.open_eye_platform;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
    super(message);
    }
}
