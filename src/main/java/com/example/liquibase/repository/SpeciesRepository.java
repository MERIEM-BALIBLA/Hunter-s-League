package com.example.liquibase.repository;

import com.example.liquibase.domain.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species, Integer> {
}
