package com.example.liquibase.service.interfaces;

import com.example.liquibase.domain.User;

import java.util.Optional;

public interface UserInterface {
    public User createUser(User user);
    public User getUser(int userId);
    Optional<User> getUserByEmail(String email);
}
