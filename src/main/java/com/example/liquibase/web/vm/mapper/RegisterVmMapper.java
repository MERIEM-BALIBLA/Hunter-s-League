package com.example.liquibase.web.vm.mapper;

import com.example.liquibase.domain.User;
import com.example.liquibase.web.vm.RegisterVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterVmMapper {
//    User ToUser(RegisterVM signUpVm);
    User toUser(RegisterVM signUpVm);  // Changed from ToUser to toUser

}
