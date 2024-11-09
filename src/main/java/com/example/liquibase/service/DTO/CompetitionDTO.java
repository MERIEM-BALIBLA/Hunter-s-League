package com.example.liquibase.service.DTO;

import com.example.liquibase.domain.enums.SpeciesType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionDTO {

    private String code;
    private String location;
    private LocalDateTime date;
    private SpeciesType speciesType;
    private Integer maxParticipants;
    private Boolean openRegistration;

    @Override
    public String toString() {
        return String.format("code = {id=%s, location ='%s', date ='%s', species type='%s', max members ='%s'}",
                code, location, date, speciesType, maxParticipants, openRegistration);
    }
}
