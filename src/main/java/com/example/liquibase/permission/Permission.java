package com.example.liquibase.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    GET_USERS("users:read"),
    CREATE_USERS("users:create"),
    UPDATE_USERS("users:update"),
    DELETE_USERS("users:delete"),

    GET_COMPETITION("competition:read"),
    CREATE_COMPETITION("competition:create"),
    UPDATE_COMPETITION("competition:update"),
    DELETE_COMPETITION("competition:delete"),

    GET_SPECIES("species:read"),
    CREATE_SPECIES("species:create"),
    UPDATE_SPECIES("species:update"),
    DELETE_SPECIES("species:delete"),

    GET_PARTICIPATION("participation:read"),
    CREATE_PARTICIPATION("participation:create"),
    UPDATE_PARTICIPATION("participation:update"),
    DELETE_PARTICIPATION("participation:delete"),

    GET_HUNT("hunt:read"),
    CREATE_HUNT("hunt:create"),
    UPDATE_HUNT("hunt:update"),
    DELETE_HUNT("hunt:delete"),
    GET_PARTICIPATION_USER("participation:userParticipation"),
    GET_TOPTHREE("participation:getThree");

    @Getter
    private final String permission;
}
