package com.example.liquibase.web.api;

import com.example.liquibase.domain.User;
import com.example.liquibase.service.AuthenticationService;
import com.example.liquibase.web.vm.LoginVM;
import com.example.liquibase.web.vm.RegisterVM;
import com.example.liquibase.web.vm.mapper.LoginVmMapper;
import com.example.liquibase.web.vm.mapper.RegisterVmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Call the logout method in AuthenticationService
        String logoutMessage = service.logout();

        // Return a response to the client
        return ResponseEntity.ok(logoutMessage);
    }
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