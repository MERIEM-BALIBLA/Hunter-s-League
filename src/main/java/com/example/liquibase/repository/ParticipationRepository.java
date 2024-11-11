package com.example.liquibase.repository;

import com.example.liquibase.domain.Participation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
    Optional<Participation> findById(UUID id);

    Page<Participation> findAllByOrderByIdDesc(Pageable pageable);

    List<Participation> findByUserId(UUID user_id);

}
