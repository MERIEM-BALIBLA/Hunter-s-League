package com.example.liquibase.service.interfaces;

import com.example.liquibase.domain.Species;

import java.util.Optional;

public interface SpeciesInterface {
    Optional<Species> getByName(String name);
    Optional<Species> getById(int id);
}
