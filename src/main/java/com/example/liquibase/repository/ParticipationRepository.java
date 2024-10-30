package com.example.liquibase.repository;

import com.example.liquibase.domain.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
}
