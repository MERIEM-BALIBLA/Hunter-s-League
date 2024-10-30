package com.example.liquibase.service.implementations;

import com.example.liquibase.domain.User;
import com.example.liquibase.repository.UserRepository;
import com.example.liquibase.service.interfaces.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User getUser(int userId) {
        return this.userRepository.getById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return this.userRepository.getUserByEmail(email);
    }


 /*   public LoginVM login(LoginDTO loginDTO) {
        // Check if a user with the given email exists
        User user = this.getUserByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UserExcption("Invalid username or password"));

        // Create and return a LoginVM with user information
//        return new LoginVM(user.getId(), user.getEmail(), user.getRole()); // Example fields
    }*/

}
