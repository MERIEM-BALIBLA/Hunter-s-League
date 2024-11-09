package com.example.liquibase.service.DTO.mapper;

import com.example.liquibase.domain.Competition;
import com.example.liquibase.service.DTO.CompetitionDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    Competition toCompetition(CompetitionDTO competitionDTO);
    CompetitionDTO toCompetitionDTO(Competition competition);
    List<CompetitionDTO> toCompetitionDTOs(List<Competition> competitions);
}
