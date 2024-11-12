package com.example.liquibase.web.vm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HuntVM {
    private UUID id;

    @NotNull(message = "Species shouldn't be null")
    @NotBlank(message = "Species name shouldn't be empty")
    private String speciesName;

    @NotNull(message = "Participation id shouldn't be null")
//    @NotBlank(message = "Participation id shouldn't be empty")
    private UUID participationId;

    @NotNull(message = "Weight shouldn't be null")
    private Double weight;
}
