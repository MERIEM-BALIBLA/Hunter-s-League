package com.example.liquibase.web.vm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationVM {
    @NotNull
    @NotBlank(message = "User Name is required.")
    private String userName;

    @NotNull
    @NotBlank(message = "You need the enter the competition code")
    private String competitionCode;
}