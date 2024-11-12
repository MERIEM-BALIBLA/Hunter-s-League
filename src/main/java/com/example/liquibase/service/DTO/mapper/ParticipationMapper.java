package com.example.liquibase.service.DTO.mapper;

import com.example.liquibase.domain.Participation;
import com.example.liquibase.service.DTO.ParticipationDTO;
import com.example.liquibase.web.vm.ParticipationVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "competitionLocation", source = "competition.location")

    ParticipationDTO toParticipationDTO(Participation participation);

    List<ParticipationDTO> toParticipationDTOs(List<Participation> participations);

//    ParticipationVM toParticipationVM(ParticipationDTO participationDTO);

//    List<ParticipationVM> toParticipationVMs(List<ParticipationDTO> participationDTOs);

    // Nouveau mappage pour convertir ParticipationVM en Participation

    @Mapping(source = "userName", target = "user.firstName")
    @Mapping(source = "competitionCode", target = "competition.code")
    Participation toParticipation(ParticipationVM participation);

}
