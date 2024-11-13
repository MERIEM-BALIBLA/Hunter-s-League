package com.example.liquibase.service.DTO.mapper;

import com.example.liquibase.domain.Participation;
import com.example.liquibase.service.DTO.UserParticipationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserParticipationMapper {

    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "competitionCode", source = "competition.code")
    @Mapping(target = "competitionLocation", source = "competition.location")
    @Mapping(target = "speciesType", source = "competition.speciesType")
    UserParticipationDTO toDto(Participation participation);

    List<UserParticipationDTO> toDtoList(List<Participation> participations);
}
