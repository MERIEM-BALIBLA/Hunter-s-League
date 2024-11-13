package com.example.liquibase.service.DTO;

import com.example.liquibase.domain.enums.SpeciesType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserParticipationDTO {
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String competitionCode;
    private String competitionLocation;
    private SpeciesType speciesType;
    private Double score;
}

