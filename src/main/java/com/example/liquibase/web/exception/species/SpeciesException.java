package com.example.liquibase.web.exception.species;

public class SpeciesException extends RuntimeException {
    public SpeciesException(String message) {
        super(message);
    }

    public SpeciesException(String message, Throwable cause) {
        super(message, cause);
    }
}
