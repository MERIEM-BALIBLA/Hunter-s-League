package com.example.liquibase.repository;

import com.example.liquibase.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
    public Optional<Competition> getById(UUID id);
}
