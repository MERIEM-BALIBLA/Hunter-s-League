package com.example.liquibase.service.DTO.mapper;

import com.example.liquibase.domain.Hunt;
import com.example.liquibase.domain.Participation;
import com.example.liquibase.service.DTO.HuntDTO;
import com.example.liquibase.web.vm.HuntVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HuntMapper {
    @Mapping(target = "speciesname", source = "species.name")
    @Mapping(target = "participationId", source = "participation.id")
    HuntDTO toHuntDTO(Hunt hunt);

//    List<HuntDTO> toHuntDTOs(List<Hunt> hunts);


   /* @Mapping(source = "speciesName", target = "species.name")
    @Mapping(source = "participationId", target = "participation.id")
    @Mapping(source = "weight", target = "hunt.weight")*/
    Participation toHunt(HuntVM hunt);
}
