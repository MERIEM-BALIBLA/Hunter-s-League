package com.example.liquibase.service.interfaces;

import com.example.liquibase.domain.Competition;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface CompetitionInterface {
    Competition save(Competition competition);

    Competition update(Competition competition);

    void delete(UUID id);

    public Optional<Competition> getByUd(UUID id);

    Page<Competition> getAll(int page, int size);

    Optional<Competition> getByCode(String code);
}
