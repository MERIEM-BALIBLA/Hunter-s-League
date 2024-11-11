package com.example.liquibase.repository;

import com.example.liquibase.domain.Hunt;
import com.example.liquibase.domain.enums.SpeciesType;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HuntRepository extends JpaRepository<Hunt, Integer> {
    Optional<Hunt> findById(UUID id);

    Optional<Hunt> findBySpeciesName(String name);

    List<Hunt> findByWeight(Double weight);

//    Page<Hunt> findAllOrderById(Pageable pageable);
}
