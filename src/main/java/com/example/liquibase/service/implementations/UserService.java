package com.example.liquibase.service.implementations;

import com.example.liquibase.domain.User;
import com.example.liquibase.repository.UserRepository;
import com.example.liquibase.service.DTO.UserDTO;
import com.example.liquibase.service.interfaces.UserInterface;
import com.example.liquibase.web.exception.user.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getExpiredUsers() {
        LocalDateTime now = LocalDateTime.now();
        List<User> expiredUsers = userRepository.findByLicenseExpirationDateAfter(now);
        return expiredUsers.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail()))
                .collect(Collectors.toList());    }
    public Optional<User> getUserByName(String username) {
        return this.userRepository.getUserByUsername(username);
    }

    public Page<User> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllByOrderByJoinDateDesc(pageable);
    }

    @Override
    public User createUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));  // Remove asterisks
        Optional<User> userOptional = this.getUserByName(user.getUsername());
        if (userOptional.isPresent()) {
            throw new UserException("User already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> login(User user) {
        if (user == null) {
            throw new UserException("User is null");
        }
        Optional<User> userOptional = this.getUserByName(user.getUsername());
        if (userOptional.isEmpty()) {
            throw new UserException("Username or password is incorrect");
        }
        User foundUser = userOptional.get();
        if (!BCrypt.checkpw(user.getPassword(), foundUser.getPassword())) {
            throw new UserException("Username or password is incorrect");
        }
        return userOptional;
    }

    @Override
    public void deleteUser(UUID userId) {
        Optional<User> userOptional = getUserById(userId);
        if (userOptional.isEmpty()) {
            throw new UserException("User not found");
        }
        userRepository.delete(userOptional.get());
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.getUserById(id);
    }

    public User updateUser(UUID userId, User updatedUser) {
        Optional<User> userOptional = getUserById(userId);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();

            existingUser.setPassword(BCrypt.hashpw(existingUser.getPassword(), BCrypt.gensalt()));  // Remove asterisks
            // Mettez à jour les champs nécessaires avec les valeurs de updatedUser
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setNationality(updatedUser.getNationality());
            existingUser.setRole(updatedUser.getRole());
            return userRepository.save(existingUser);

        } else {
            throw new RuntimeException("User not found");
        }
    }

    public List<User> searchUsersByUsername(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new UserException("Search term cannot be empty");
        }
        return userRepository.findByUsernameContainingIgnoreCase(searchTerm.trim());
    }

    public List<User> searchUserByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new UserException("Email cannot be empty");
        }
        return userRepository.findByEmail(email.trim());
    }

}
