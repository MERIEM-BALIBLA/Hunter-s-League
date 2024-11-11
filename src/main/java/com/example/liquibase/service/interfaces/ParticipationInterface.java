package com.example.liquibase.service.interfaces;

import com.example.liquibase.domain.Participation;
import com.example.liquibase.service.DTO.ParticipationDTO;
import com.example.liquibase.web.vm.ParticipationVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ParticipationInterface {

//    Page<ParticipationDTO> findAll(Pageable pageable);

    Participation save(ParticipationVM participationVM);

    Optional<Participation> findById(UUID id);

    Participation update(Participation participation);

    void delete(UUID id);
}
