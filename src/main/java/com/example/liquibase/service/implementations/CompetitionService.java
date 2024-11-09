package com.example.liquibase.service.implementations;

import com.example.liquibase.domain.Competition;
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
import java.util.Optional;
import java.util.UUID;

@Service

public class CompetitionService implements CompetitionInterface {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkAndCloseRegistration() {
        // Récupérer toutes les compétitions
        competitionRepository.findAll().forEach(competition -> {
            LocalDateTime competitionDate = competition.getDate();
            long daysUntilCompetition = ChronoUnit.DAYS.between(LocalDateTime.now(), competitionDate);

            if (daysUntilCompetition <= 3 && competition.getOpenRegistration()) {
                competition.setOpenRegistration(false);
                competitionRepository.save(competition);
            }
        });
    }

    /* @Scheduled(cron = "0 0 0 * * ?") // Toutes les 5 minutes
     public void checkAndCloseRegistration() {
         List<Competition> competitions = competitionRepository.findAll();

         for (Competition competition : competitions) {
             LocalDateTime competitionDate = competition.getDate(); // Date de la compétition
             LocalDateTime now = LocalDateTime.now(); // Date et heure actuelles

             long minutesUntilCompetition = ChronoUnit.MINUTES.between(now, competitionDate);

             if (minutesUntilCompetition <= 3 && minutesUntilCompetition > 0) {
                 // Fermer l'inscription
                 competition.setOpenRegistration(false);
                 competitionRepository.save(competition);
             }
         }
     }
 */
    @Override
    public Optional<Competition> getByCode(String code) {
        return competitionRepository.findByCode(code);
    }

    @Override
    public Competition save(Competition competition) {
        // First, generate the competition code if it's not already set
        if (competition.getLocation() != null && competition.getDate() != null && competition.getCode() == null) {
            String code = generateCodeFromLocationAndDate(competition.getLocation(), competition.getDate());
            competition.setCode(code);
        }

        // Now check if a competition with the generated code already exists
        Optional<Competition> competitionOptional = getByCode(competition.getCode());
        if (competitionOptional.isPresent()) {
            throw new CompetitionException("This competition with this code already exists");
        }

        // Save the new competition
        return competitionRepository.save(validation(competition));
    }

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
    public Competition update(UUID id, Competition competition) {
        Optional<Competition> competitionOptional = getByUd(id);
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
}
