package com.example.liquibase.web.vm.mapper;

import com.example.liquibase.domain.User;
import com.example.liquibase.web.vm.LoginVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginVmMapper {
    User ToUser(LoginVM loginVM);
}