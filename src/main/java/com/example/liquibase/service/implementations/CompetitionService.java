package com.example.liquibase.service.implementations;

import com.example.liquibase.domain.Competition;
import com.example.liquibase.domain.enums.SpeciesType;
import com.example.liquibase.repository.CompetitionRepository;
import com.example.liquibase.service.interfaces.CompetitionInterface;
import com.example.liquibase.web.exception.Competition.CompetitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class CompetitionService implements CompetitionInterface {

    @Autowired
    private CompetitionRepository competitionRepository;

    public List<Competition> getAll() {
        return competitionRepository.findAll();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkAndCloseRegistration() {
        getAll().forEach(competition -> {
            LocalDateTime competitionDate = competition.getDate();
            long daysUntilCompetition = ChronoUnit.DAYS.between(LocalDateTime.now(), competitionDate);

            if (daysUntilCompetition <= 3 && competition.getOpenRegistration()) {
                competition.setOpenRegistration(false);
                update(competition);
            }
        });
    }

    @Override
    public Optional<Competition> getByCode(String code) {
        return competitionRepository.findByCode(code);
    }

    @Override
    public Competition save(Competition competition) {
        if (competition.getLocation() != null && competition.getDate() != null && competition.getCode() == null) {
            String code = generateCodeFromLocationAndDate(competition.getLocation(), competition.getDate());
            competition.setCode(code);
            competition.setOpenRegistration(true);
        }

        Optional<Competition> competitionOptional = getByCode(competition.getCode());
        if (competitionOptional.isPresent()) {
            throw new CompetitionException("This competition with this code already exists");
        }

        LocalDateTime currentDate = competition.getDate();
        LocalDateTime sevenDaysAgo = currentDate.minusDays(7);

        List<Competition> existingCompetitions = competitionRepository.findByDateBetween(sevenDaysAgo, currentDate);
        if (!existingCompetitions.isEmpty()) {
            throw new CompetitionException("Another competition already exists in the previous 7 days");
        }

        if (competition.getDate().isBefore(LocalDateTime.now())) {
            throw new CompetitionException("The competition date must be in the future");
        }

        if (competition.getMinParticipants() > competition.getMaxParticipants()) {
            throw new CompetitionException("The value of the minimum shouldn't be grater than the maximum");
        }

        // Save the new competition
        return competitionRepository.save(competition);
    }
    /*public Competition save(Competition competition) {
        if (competition.getLocation() != null && competition.getDate() != null && competition.getCode() == null) {
            String code = generateCodeFromLocationAndDate(competition.getLocation(), competition.getDate());
            competition.setCode(code);
        }

        Optional<Competition> competitionOptional = getByCode(competition.getCode());
        if (competitionOptional.isPresent()) {
            throw new CompetitionException("This competition with this code already exists");
        }

        // Save the new competition
        return competitionRepository.save(validation(competition));
    }*/

    private String generateCodeFromLocationAndDate(String location, LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = date.format(formatter);
        return location + "-" + formattedDate;
    }

    @Override
    public Optional<Competition> getByUd(UUID id) {
        return competitionRepository.getById(id);
    }

    @Override
    public Competition update(Competition competition) {
        Optional<Competition> competitionOptional = getByUd(competition.getId());
        if (competitionOptional.isPresent()) {
            Competition existingCompetition = competitionOptional.get();

            existingCompetition.setCode(competition.getCode());
            existingCompetition.setLocation(competition.getLocation());
            existingCompetition.setDate(competition.getDate());
            existingCompetition.setMinParticipants(competition.getMinParticipants());
            existingCompetition.setMaxParticipants(competition.getMaxParticipants());
            existingCompetition.setSpeciesType(competition.getSpeciesType());
            existingCompetition.setOpenRegistration(competition.getOpenRegistration());

            return competitionRepository.save(existingCompetition);

        } else {
            throw new RuntimeException("Competition not found");
        }
    }

    @Override
    public void delete(UUID id) {
        Optional<Competition> competition = getByUd(id);
        if (competition.isEmpty()) {
            throw new CompetitionException("Competition not found");
        }
        competitionRepository.delete(competition.get());
    }

    @Override
    public Page<Competition> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return competitionRepository.findAll(pageable);
    }

    public Competition validation(Competition competition) {
        if (competition.getDate().isBefore(LocalDateTime.now().plusDays(3))) {
            throw new CompetitionException("Competition date must be at least 3 days from now");
        }
        return competition;
    }

    public Optional<Competition> findByLocation(String location) {
        return competitionRepository.findByLocation(location);
    }

//    public List<Competition> searchCompetitions(String code, String location, LocalDateTime dateFrom, SpeciesType speciesType, Boolean openRegistration) {
//        return competitionRepository.findByCriteria();
//    }
}
