package com.example.liquibase.service.interfaces;

import com.example.liquibase.domain.Participation;
import com.example.liquibase.service.DTO.ParticipationDTO;
import com.example.liquibase.service.DTO.UserParticipationDTO;
import com.example.liquibase.web.vm.ParticipationVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipationInterface {

//    Page<ParticipationDTO> findAll(Pageable pageable);

    Page<Participation> findAll(int page, int size);

    Participation save(ParticipationVM participationVM);

    Optional<Participation> findById(UUID id);

    Participation update(Participation participation);

    void delete(UUID id);

    List<UserParticipationDTO> findByUserId(UUID id);

    List<ParticipationDTO> getTop3Participants();
}
