package com.example.liquibase.service.implementations;

import com.example.liquibase.domain.Species;
import com.example.liquibase.domain.User;
import com.example.liquibase.repository.SpeciesRepository;
import com.example.liquibase.web.exception.user.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SpeciesService {

    @Autowired
    private SpeciesRepository speciesRepository;

    public Species createSpecie(Species species) {
        Optional<Species> userOptional = this.speciesRepository.getByName(species.getName());
        if (userOptional.isPresent()) {
            throw new UserException("User already exists");
        }
        return speciesRepository.save(species);
    }

    public Optional<Species> getSpeciesById(UUID id) {
        return speciesRepository.getSpeciesById(id);
    }

    public void deleteSpecies(UUID specieId) {
        Optional<Species> userOptional = getSpeciesById(specieId);
        if (userOptional.isEmpty()) {
            throw new UserException("User not found");
        }
        speciesRepository.delete(userOptional.get());
    }

    public Species updateSpecies(Species species) {
        Optional<Species> speciesOptional = getSpeciesById(species.getId());
        if (speciesOptional.isPresent()) {
            Species existingSpecies = speciesOptional.get();

            existingSpecies.setName(species.getName());

            return speciesRepository.save(existingSpecies);
        } else {
            throw new RuntimeException("Species not found");
        }
    }


}
