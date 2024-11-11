package com.example.liquibase.service.implementations;

import com.example.liquibase.domain.Competition;
import com.example.liquibase.domain.Participation;
import com.example.liquibase.domain.User;
import com.example.liquibase.repository.ParticipationRepository;
import com.example.liquibase.service.DTO.ParticipationDTO;
import com.example.liquibase.service.DTO.mapper.ParticipationMapper;
import com.example.liquibase.service.interfaces.ParticipationInterface;
import com.example.liquibase.web.vm.ParticipationVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ParticipationService implements ParticipationInterface {

    private final ParticipationRepository participationRepository;
    private final ParticipationMapper participationMapper;
    private final CompetitionService competitionService;
    private final UserService userService;

    public ParticipationService(ParticipationRepository participationRepository, ParticipationMapper participationMapper, CompetitionService competitionService, UserService userService) {
        this.participationRepository = participationRepository;
        this.participationMapper = participationMapper;
        this.competitionService = competitionService;
        this.userService = userService;
    }

    /*   @Override
       public Page<ParticipationDTO> findAll(Pageable pageable) {
           Page<Participation> participationPage = participationRepository.findAllByOrderByIdDesc(pageable);
           return participationPage.map(participationMapper::toParticipationDTO);
       }*/
    public Page<Participation> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return participationRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public Participation save(ParticipationVM participationVM) {
        Optional<User> user = userService.getUserByName(participationVM.getUserName());
        Optional<Competition> competition = competitionService.getByCode(participationVM.getCompetitionCode());

        // Map the ParticipationVM to Participation
        Participation participation = participationMapper.toParticipation(participationVM);

        // Set the user and competition associations
        participation.setUser(user.get());
        participation.setCompetition(competition.get());

        return participationRepository.save(participation);

        // Convert the saved Participation entity back to a DTO if needed
//        return participationMapper.toParticipationDTO(participation);
    }

    @Override
    public Optional<Participation> findById(UUID id) {
        return participationRepository.findById(id);
    }

    @Override
    public Participation update(Participation participation) {
        Optional<Participation> optionalParticipation = findById(participation.getId());
        if (optionalParticipation.isPresent()) {
            Participation existingParticipation = optionalParticipation.get();
            existingParticipation.setCompetition(participation.getCompetition());
            existingParticipation.setUser(participation.getUser());
            existingParticipation.setScore(participation.getScore());

            return participationRepository.save(existingParticipation);
        } else {
            throw new RuntimeException("Participation not found");
        }
    }

    @Override
    public void delete(UUID id) {
        Optional<Participation> optionalParticipation = findById(id);
        if (optionalParticipation.isPresent()) {
            participationRepository.delete(optionalParticipation.get());
        } else {
            throw new RuntimeException("Participation not found");
        }
    }

    public List<ParticipationDTO> findByUserId(UUID id) {
        return participationRepository.findByUserId(id).stream().map(participationMapper::toParticipationDTO).collect(Collectors.toList());
    }

}
