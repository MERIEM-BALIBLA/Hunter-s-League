package com.example.liquibase.web.exception.Competition;

public class CompetitionException extends RuntimeException{
    public CompetitionException(String message) {
        super(message);
    }
    public CompetitionException(String message, Throwable cause) {
        super(message, cause);
    }
}


