package com.example.liquibase.service.interfaces;

import com.example.liquibase.domain.Competition;

import java.util.Optional;
import java.util.UUID;

public interface CompetitionInterface {
    Competition save(Competition competition);

    Competition update(UUID id, Competition competition);

    void delete(UUID id);

    public Optional<Competition> getByUd(UUID id);
}
