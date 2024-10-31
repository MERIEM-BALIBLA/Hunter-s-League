package com.example.liquibase.web.api.user;

import com.example.liquibase.domain.User;
import com.example.liquibase.service.implementations.UserService;
import com.example.liquibase.web.vm.LoginVM;
import com.example.liquibase.web.vm.RegisterVM;
import com.example.liquibase.web.vm.mapper.LoginVmMapper;
import com.example.liquibase.web.vm.mapper.RegisterVmMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final RegisterVmMapper registerVmMapper;
    private final LoginVmMapper loginVmMapper;

    public UserController(UserService userService, RegisterVmMapper registerVmMapper, LoginVmMapper loginVmMapper) {
        this.userService = userService;
        this.registerVmMapper = registerVmMapper;
        this.loginVmMapper = loginVmMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody @Valid RegisterVM signUpVm) {
        User user = registerVmMapper.toUser(signUpVm);
        User userCreated = userService.createUser(user);
        return ResponseEntity.ok(userCreated);
    }

    /* @PostMapping("/login")
     public ResponseEntity<String> login(@Valid  @RequestBody LoginVM signInVm) {
         User user = loginVmMapper.ToUser(signInVm);
         userService.login(user);
         return ResponseEntity.ok("User logged in successfully");
     }*/
    @PostMapping("/login")
    public ResponseEntity<Optional<User>> login(@Valid @RequestBody LoginVM signInVm) {
        User user = loginVmMapper.ToUser(signInVm);
        Optional<User> loggedInUser = userService.login(user);
        return ResponseEntity.ok(loggedInUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User has been deleted succesfully");
    }

}
