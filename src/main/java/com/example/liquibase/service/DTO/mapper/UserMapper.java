package com.example.liquibase.service.DTO.mapper;

import com.example.liquibase.domain.User;
import com.example.liquibase.service.DTO.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
    List<UserDTO> toUserDTOs(List<User> users);
}