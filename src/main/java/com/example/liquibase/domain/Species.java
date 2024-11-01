package com.example.liquibase.domain;

import com.example.liquibase.domain.enums.Difficulty;
import com.example.liquibase.domain.enums.SpeciesType;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
//import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @NotBlank
    @Size(min = 4, message = "the name must contain at least 4 characters")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SpeciesType category;

    @NotNull
    @Min(value = 20, message = "the minimum weight must be 20 or greater")
    private Double minimumWeight;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Min(value = 0, message = "points must be 0 or greater")
    private Integer points;
}
