package com.example.liquibase.service.DTO;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    @Override
    public String toString() {
        return String.format("UserDTO{id=%s, username='%s', firstName='%s', lastName='%s', email='%s'}",
                id, username, firstName, lastName, email);
    }
}
