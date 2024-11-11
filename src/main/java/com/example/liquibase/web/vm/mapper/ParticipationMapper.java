//package com.example.liquibase.web.vm.mapper;
//
//import com.example.liquibase.domain.Participation;
//import com.example.liquibase.web.vm.ParticipationVM;
//import com.example.liquibase.service.DTO.ParticipationDTO;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface ParticipationMapper {
//
//    ParticipationVM toVM(ParticipationDTO dto);
//
//    List<ParticipationVM> toVMs(List<ParticipationDTO> dtos);
//
//    @Mapping(target = "userName", source = "userName")
//    @Mapping(target = "competitionLocation", source = "competitionLocation")
//    @Mapping(target = "score", source = "score")
//    ParticipationDTO toDTO(ParticipationVM vm);
//
//    @Mapping(target = "user", ignore = true)
//    @Mapping(target = "competition", ignore = true)
//    @Mapping(target = "hunts", ignore = true)
//    Participation toEntity(ParticipationVM vm);
//
//    void updateParticipationFromVM(ParticipationVM vm, @MappingTarget Participation participation);
//}