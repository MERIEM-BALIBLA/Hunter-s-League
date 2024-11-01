package com.example.liquibase.repository;

import com.example.liquibase.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    Optional<Species> getByName(String name);
    Optional<Species> getSpeciesById(UUID id);
}
