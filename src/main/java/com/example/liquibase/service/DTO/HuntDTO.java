package com.example.liquibase.service.DTO;

import com.example.liquibase.domain.enums.SpeciesType;
import java.util.UUID;

public class HuntDTO {
    private UUID id;
    private String speciesname;
    private Double weight;
    private UUID participationId;

    // Getters
    public UUID getId() {
        return id;
    }

    public String getSpeciesname() {
        return speciesname;
    }

    public Double getWeight() {
        return weight;
    }

    public UUID getParticipationId() {
        return participationId;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setSpeciesname(String speciesname) {
        this.speciesname = speciesname;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setParticipationId(UUID participationId) {
        this.participationId = participationId;
    }
}