package com.example.liquibase.web.exception.hunt;

public class HuntException extends RuntimeException {
    public HuntException(String message) {
        super(message);
    }

    public HuntException(String message, Throwable cause) {
        super(message, cause);
    }
}
