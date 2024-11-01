package com.example.liquibase.web.api.user;

import com.example.liquibase.domain.User;
import com.example.liquibase.service.DTO.mapper.UserMapper;
import com.example.liquibase.service.implementations.UserService;
import com.example.liquibase.web.vm.LoginVM;
import com.example.liquibase.web.vm.RegisterVM;
import com.example.liquibase.web.vm.mapper.LoginVmMapper;
import com.example.liquibase.web.vm.mapper.RegisterVmMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.liquibase.service.DTO.UserDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final RegisterVmMapper registerVmMapper;
    private final LoginVmMapper loginVmMapper;
    private final UserMapper userMapper;

    public UserController(UserService userService, RegisterVmMapper registerVmMapper, LoginVmMapper loginVmMapper, UserMapper userMapper) {
        this.userService = userService;
        this.registerVmMapper = registerVmMapper;
        this.loginVmMapper = loginVmMapper;
        this.userMapper = userMapper;
    }

    /*    @GetMapping("/list")
        public List<UserDTO> getAll() {
            List<User> users = userService.getAll();
            List<UserDTO> userDTOs = userMapper.toUserDTOs(users);
            return ResponseEntity.ok(userDTOs).getBody();
        }*/
    @GetMapping("/list")
    public ResponseEntity<Page<UserDTO>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<User> userPage = userService.getAll(page, size);
        Page<UserDTO> userDTOPage = userPage.map(userMapper::toUserDTO);
        return ResponseEntity.ok(userDTOPage);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid RegisterVM signUpVm) {
        User user = registerVmMapper.toUser(signUpVm);
        User userCreated = userService.createUser(user);
        UserDTO userDto = userMapper.toUserDTO(userCreated);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginVM signInVm) {
        User user = loginVmMapper.ToUser(signInVm);
        Optional<User> loggedInUser = userService.login(user);
        UserDTO userDTO = userMapper.toUserDTO(loggedInUser.get());
        return ResponseEntity.ok("welcome back " + userDTO.getUsername());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User has been deleted succesfully");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") UUID userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        List<User> users = userService.searchUserByEmail(email);

        // Use the mapper to convert User to UserDTO
        List<UserDTO> userDTOs = userMapper.toUserDTOs(users);

        return ResponseEntity.ok(userDTOs);
    }

}
