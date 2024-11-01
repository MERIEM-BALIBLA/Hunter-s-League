package com.example.liquibase.service.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ParticipationDTO {
    private UUID id;
    // Add relevant participation fields
    private LocalDateTime participationDate;
    // Don't include user to avoid circular reference
}
