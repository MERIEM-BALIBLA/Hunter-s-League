package com.example.liquibase.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    MANAGE_USER("user: management"),

    MANAGE_COMPETITION("competition: management"),
    CAN_VIEW_COMPETITIONS("competition:CAN VIEW COMPETITIONS"),

    MANAGE_SPECIES("species: management"),
    CAN_VIEW_SPECIES("species:read"),

    MANAGE_PARTICIPATION("participation: management"),
    GET_PARTICIPATION_USER("participation: participation of user auth"),

    MANAGE_HUNT("hunt: hunt CRUD"),
    CAN_VIEW_RANKINGS("les trois premier:read");

    @Getter
    private final String permission;
}
