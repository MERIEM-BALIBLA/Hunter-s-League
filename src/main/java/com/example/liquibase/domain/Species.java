package com.example.liquibase.domain;

import com.example.liquibase.domain.enums.Difficulty;
import com.example.liquibase.domain.enums.SpeciesType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Enumerated(EnumType.STRING)
    private SpeciesType category;

    @NotNull
    @NotBlank
    @Size(min = 10 , message = ("the weight must be or gratest than 10"))
    private Double minimumWeight;

    @NotNull
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    private Integer points;

}
