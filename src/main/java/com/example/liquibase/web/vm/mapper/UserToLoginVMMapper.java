package com.example.liquibase.web.vm.mapper;

import com.example.liquibase.domain.User;
import com.example.liquibase.web.vm.LoginVM;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public class UserToLoginVMMapper {
    public LoginVM map(User user) {
        LoginVM loginVM = new LoginVM();
        loginVM.setEmail(user.getEmail());
        return loginVM;
    }
}