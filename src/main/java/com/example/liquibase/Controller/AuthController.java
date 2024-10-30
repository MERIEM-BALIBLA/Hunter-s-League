package com.example.liquibase.Controller;

import com.example.liquibase.web.vm.LoginVM;

public class AuthController {
    private final LoginVM userViewModel;
    public AuthController(LoginVM userViewModel) {
        this.userViewModel = userViewModel;
    }
}
