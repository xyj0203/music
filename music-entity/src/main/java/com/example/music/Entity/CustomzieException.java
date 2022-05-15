package com.example.music.Entity;

public class CustomzieException extends RuntimeException {
    public CustomzieException(String message) {
        super(message);
    }

    public CustomzieException(String message, Throwable cause) {
        super(message, cause);
    }
}
