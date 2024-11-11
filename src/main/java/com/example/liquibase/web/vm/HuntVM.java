package com.example.liquibase.web.vm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HuntVM {
    private String speciesname;
    private UUID participationId;
    private Double weight;
}
