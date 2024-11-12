package com.example.liquibase.repository;

import com.example.liquibase.domain.Participation;
import com.example.liquibase.service.DTO.ParticipationDTO;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.jpa.HibernateHints.HINT_FETCH_SIZE;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
    Optional<Participation> findById(UUID id);

    Page<Participation> findAllByOrderByIdDesc(Pageable pageable);

    List<Participation> findByUserId(UUID user_id);

    Optional<Participation> findParticipationByUserIdAndCompetitionId(UUID user_id, UUID competition_id);

    List<Participation> findTop3ByOrderByScoreDesc();

    @Query("SELECT COUNT(DISTINCT p.user) FROM Participation p WHERE p.competition.id = :competitionId")
    int countUsersByCompetitionId(UUID competitionId);

}