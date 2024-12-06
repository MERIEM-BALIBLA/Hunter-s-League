package com.example.liquibase.web.api.user;

import com.example.liquibase.domain.User;
import com.example.liquibase.service.DTO.mapper.UserMapper;
import com.example.liquibase.service.implementations.UserService;
import com.example.liquibase.service.interfaces.UserInterface;
import com.example.liquibase.web.vm.RegisterVM;
import com.example.liquibase.web.vm.mapper.LoginVmMapper;
import com.example.liquibase.web.vm.mapper.RegisterVmMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.liquibase.service.DTO.UserDTO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserInterface userService;
    private final RegisterVmMapper registerVmMapper;
    private final UserMapper userMapper;

    public UserController(UserService userService, RegisterVmMapper registerVmMapper, LoginVmMapper loginVmMapper, UserMapper userMapper) {
        this.userService = userService;
        this.registerVmMapper = registerVmMapper;
        this.userMapper = userMapper;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('MANAGE_USER')")
    public ResponseEntity<Page<UserDTO>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<User> userPage = userService.getAll(page, size);
        Page<UserDTO> userDTOPage = userPage.map(userMapper::toUserDTO);
        return ResponseEntity.ok(userDTOPage);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('MANAGE_USER')")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid RegisterVM signUpVm) {
        User user = registerVmMapper.toUser(signUpVm);
        User userCreated = userService.createUser(user);
        UserDTO userDto = userMapper.toUserDTO(userCreated);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('MANAGE_USER')")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User has been deleted succesfully");
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('MANAGE_USER')")
    public ResponseEntity<User> updateUser(@PathVariable("userId") UUID userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(user);
    }

    //    @PostMapping("/search")
//    public ResponseEntity<List<UserDTO>> searchUsers(@RequestBody Map<String, String> body) {
//        String email = body.get("email");
//        List<User> users = userService.searchUserByEmail(email);
//
//        // Use the mapper to convert User to UserDTO
//        List<UserDTO> userDTOs = userMapper.toUserDTOs(users);
//
//        return ResponseEntity.ok(userDTOs);
//    }
    @GetMapping("/expired")
    public List<UserDTO> getExpiredUsers() {
        return userService.getExpiredUsers();
    }


}
