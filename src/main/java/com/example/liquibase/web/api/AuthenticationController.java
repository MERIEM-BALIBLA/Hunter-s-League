package com.example.liquibase.web.api;

import com.example.liquibase.domain.User;
import com.example.liquibase.service.AuthenticationService;
import com.example.liquibase.service.DTO.UserDTO;
import com.example.liquibase.web.vm.LoginVM;
import com.example.liquibase.web.vm.RegisterVM;
import com.example.liquibase.web.vm.mapper.LoginVmMapper;
import com.example.liquibase.web.vm.mapper.RegisterVmMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;
    @Autowired
    private RegisterVmMapper registerMapper;
    @Autowired
    private LoginVmMapper loginMapper;

//    @GetMapping("/")
//    public ResponseEntity<String> welcome(HttpServletRequest request) {
//        return ResponseEntity.ok("Welcome back " + request.getSession().getId());
//    }

    @PostMapping("/register")
    public ResponseEntity<RegisterVM> register(@RequestBody RegisterVM userVm) {
        User user = registerMapper.toUser(userVm);
        User savedUser = service.register(user);
        return ResponseEntity.ok(registerMapper.toVM(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginVM loginVM) {
        User user = loginMapper.ToUser(loginVM);
        return ResponseEntity.ok(service.verify(user));
    }

//    @GetMapping("/csrf")
//    public CsrfToken getCsrf(HttpServletRequest http) {
//        return (CsrfToken) http.getAttribute("_csrf");
////        return ResponseEntity.ok("welcome " + http.getAttribute("_csrf"));
//    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        return ResponseEntity.ok("Welcome back " + user.getUsername());
//    }


}