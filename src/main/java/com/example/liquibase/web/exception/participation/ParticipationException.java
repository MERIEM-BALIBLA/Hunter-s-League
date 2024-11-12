package com.example.liquibase.web.exception.participation;

public class ParticipationException extends RuntimeException {
    public ParticipationException(String message) {
        super(message);
    }

    public ParticipationException(String message, Throwable cause) {
        super(message, cause);
    }
}
