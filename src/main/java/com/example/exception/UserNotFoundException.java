package com.example.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User not found with id: " + userId);
    }
}
