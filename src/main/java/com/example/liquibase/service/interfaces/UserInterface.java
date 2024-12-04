package com.example.liquibase.service.interfaces;

import com.example.liquibase.domain.User;
import com.example.liquibase.service.DTO.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserInterface {
    List<UserDTO> getExpiredUsers();

    Optional<User> getUserByName(String username);

    Page<User> getAll(int page, int size);

    User createUser(User user);

    Optional<User> login(User user);

    void deleteUser(UUID userId);

    Optional<User> getUserById(UUID id);

    User updateUser(UUID userId, User updatedUser);

    Optional<User> searchUserByEmail(String email);

    List<User> searchUsersByUsername(String searchTerm);

    List<User> findByLastName(String lastName);

    List<User> findByFirstName(String firstName);

    List<User> findByCin(String cin);
}
