package com.example.liquibase.web.vm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationVM {
    private String userName;
    private String competitionCode;
}