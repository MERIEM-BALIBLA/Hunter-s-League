package com.example.liquibase.repository;

import com.example.liquibase.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByEmail(String email);
}