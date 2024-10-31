package com.example.liquibase.service.interfaces;

import com.example.liquibase.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserInterface {
    User createUser(User user);

    Optional<User> login(User user);

    void deleteUser(UUID userId);

    Optional<User> getUserById(UUID id);
}
