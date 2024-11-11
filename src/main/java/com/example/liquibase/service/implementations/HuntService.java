package com.example.liquibase.service.implementations;

import com.example.liquibase.domain.Hunt;
import com.example.liquibase.repository.HuntRepository;
import com.example.liquibase.service.DTO.HuntDTO;
import com.example.liquibase.service.DTO.mapper.HuntMapper;
import com.example.liquibase.service.interfaces.HuntInterface;
import com.example.liquibase.web.exception.hunt.HuntException;
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

    public HuntService(HuntRepository huntRepository, HuntMapper huntMapper) {
        this.huntRepository = huntRepository;
        this.huntMapper = huntMapper;
    }

    @Override
    public Page<HuntDTO> findAll(Pageable pageable) {
        Page<Hunt> participationPage = huntRepository.findAll(pageable);
        return participationPage.map(huntMapper::toHuntDTO);
    }

    @Override
    public Hunt save(Hunt hunt) {
        return huntRepository.save(hunt);
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
