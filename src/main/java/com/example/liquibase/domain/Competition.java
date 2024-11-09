package com.example.liquibase.domain;

import com.example.liquibase.domain.enums.SpeciesType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    @Id
    @GeneratedValue
    private UUID id;


    private String code;

    @NotNull(message = "Location cannot be null")
    @Size(min = 3, max = 100, message = "Location must be between 3 and 100 characters")
    private String location;

    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private SpeciesType speciesType;

    @Min(value = 1, message = "Minimum participants must be at least 1")
    @Max(value = 1000, message = "Maximum participants must be less than or equal to 1000")
    private Integer minParticipants;

    @Min(value = 1, message = "Maximum participants must be at least 1")
    @Max(value = 1000, message = "Maximum participants must be less than or equal to 1000")
    private Integer maxParticipants;

    private Boolean openRegistration;

    @OneToMany(mappedBy = "competition")
    private List<Participation> participations;

}
