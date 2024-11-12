package com.example.liquibase.service.implementations;

import com.example.liquibase.domain.Hunt;
import com.example.liquibase.domain.Participation;
import com.example.liquibase.domain.Species;
import com.example.liquibase.domain.enums.Difficulty;
import com.example.liquibase.domain.enums.SpeciesType;
import com.example.liquibase.repository.HuntRepository;
import com.example.liquibase.service.DTO.HuntDTO;
import com.example.liquibase.service.DTO.mapper.HuntMapper;
import com.example.liquibase.service.interfaces.HuntInterface;
import com.example.liquibase.web.exception.hunt.HuntException;
import com.example.liquibase.web.vm.HuntVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HuntService implements HuntInterface {

    private final HuntRepository huntRepository;
    private final HuntMapper huntMapper;
    private final ParticipationService participationService;
    private final SpeciesService speciesService;

    public HuntService(HuntRepository huntRepository, HuntMapper huntMapper, ParticipationService participationService, SpeciesService speciesService) {
        this.huntRepository = huntRepository;
        this.huntMapper = huntMapper;
        this.participationService = participationService;

        this.speciesService = speciesService;
    }

    @Override
    public Page<HuntDTO> findAll(Pageable pageable) {
        Page<Hunt> participationPage = huntRepository.findAll(pageable);
        return participationPage.map(huntMapper::toHuntDTO);
    }

    @Override
    /*public Hunt save(HuntVM huntVM) {
        Optional<Participation> participation = participationService.findById(huntVM.getParticipationId());
        Optional<Species> species = speciesService.findByName(huntVM.getSpeciesName());
        Hunt hunt = huntMapper.toHunt(huntVM);

        hunt.setSpecies(species.get());
        hunt.setParticipation(participation.get());
        hunt.setWeight(hunt.getWeight());
        return huntRepository.save(hunt);
    }*/
    public Hunt save(HuntVM huntVM) {
        Optional<Participation> participationOpt = participationService.findById(huntVM.getParticipationId());
        Optional<Species> speciesOpt = speciesService.findByName(huntVM.getSpeciesName());


        if (participationOpt.isEmpty() && speciesOpt.isEmpty()) {
            throw new RuntimeException("Participation or Species not found");
        }
        Participation participation = participationOpt.get();
        Species species = speciesOpt.get();

        Optional<Hunt> existingHunt = huntRepository.findByParticipationAndSpecies(participation, species);
        if (existingHunt.isPresent()) {
            throw new HuntException("A hunt for this species already exists for this participation");
        }

        Hunt hunt = huntMapper.toHunt(huntVM);

        // Set the hunt infromationq
        hunt.setSpecies(species);
        hunt.setParticipation(participation);
        hunt.setWeight(hunt.getWeight());

        double huntScore = calculateHuntScore(species.getPoints(), hunt.getWeight(), species.getCategory(), species.getDifficulty());

        // Update participation's score
        double currentScore = participation.getScore();
        participation.setScore(currentScore + huntScore);

        // Save the updated participation
        participationService.update(participation);
        return huntRepository.save(hunt);
    }

    private double calculateHuntScore(Integer speciesPoints, double weight, SpeciesType speciesType, Difficulty difficulty) {

        double baseCore = (weight * speciesType.getValue()) * getDifficultyMultiplier(difficulty);
        return speciesPoints + baseCore;
    }

    private double getDifficultyMultiplier(Difficulty difficulty) {
        return switch (difficulty) {
            case COMMON -> 1;
            case RARE -> 2;
            case EPIC -> 3;
            case LEGENDARY -> 5;
            default -> 1.0;
        };
    }

    @Override
    public Optional<Hunt> findById(UUID id) {
        return huntRepository.findById(id);
    }

    @Override
    public Hunt update(Hunt hunt) {
        Optional<Hunt> optionalHunt = findById(hunt.getId());
        if (optionalHunt.isPresent()) {
            Hunt existingHunt = optionalHunt.get();
            existingHunt.setSpecies(hunt.getSpecies());
            existingHunt.setWeight(hunt.getWeight());
            existingHunt.setParticipation(hunt.getParticipation());

            return huntRepository.save(existingHunt);
        } else {
            throw new HuntException("Hunt not found");
        }
    }

    @Override
    public void delete(UUID id) {
        Optional<Hunt> optionalHunt = findById(id);
        if (optionalHunt.isPresent()) {
            huntRepository.delete(optionalHunt.get());
        } else {
            throw new HuntException("Hunt not found");
        }
    }

    @Override
    public Optional<Hunt> findBySpeciesName(String name) {
        return huntRepository.findBySpeciesName(name);
    }

    @Override
    public List<Hunt> findByWeight(Double weight) {
        return huntRepository.findByWeight(weight);
    }
}
