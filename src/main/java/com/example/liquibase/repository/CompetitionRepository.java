package com.example.liquibase.repository;

import com.example.liquibase.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
}
