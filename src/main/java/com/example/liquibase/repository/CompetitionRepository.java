package com.example.liquibase.repository;

import com.example.liquibase.domain.Competition;
import com.example.liquibase.domain.enums.SpeciesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
    Optional<Competition> getById(UUID id);

    Optional<Competition> findByCode(String code);

    Optional<Competition> findByLocation(String location);

    List<Competition> findByDateBetween(LocalDateTime sevenDaysAgo, LocalDateTime currentDate);

//    @Query("SELECT c FROM Competition c WHERE " +
//            "(:code is null OR c.code LIKE %:code%) AND " +
//            "(:location is null OR c.location LIKE %:location%) AND " +
//            "(:dateFrom is null OR c.date >= :dateFrom) AND " +
//            "(:speciesType is null OR c.speciesType = :speciesType) AND " +
//            "(:openRegistration is null OR c.openRegistration = :openRegistration)")
//    List<Competition> findByCriteria(
//            @Param("code") String code,
//            @Param("location") String location,
//            @Param("dateFrom") LocalDateTime dateFrom,
//            @Param("speciesType") SpeciesType speciesType,
//            @Param("openRegistration") Boolean openRegistration
//    );
}
