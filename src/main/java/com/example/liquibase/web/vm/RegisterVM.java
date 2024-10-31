package com.example.liquibase.web.vm;

import com.example.liquibase.domain.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterVM {
    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters.")
    private String password;


    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @NotBlank(message = "CIN is required.")
    @Pattern(regexp = "^[A-Z0-9]{6,12}$", message = "CIN must be alphanumeric and between 6 and 12 characters.")
    private String cin;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    private String email;

    @NotBlank(message = "Nationality is required.")
    private String nationality;

    @NotNull(message = "Role is required.")
    private Role role;
}
