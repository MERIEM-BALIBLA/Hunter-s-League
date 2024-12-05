package com.example.liquibase.domain.enums;

import com.example.liquibase.permission.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.liquibase.permission.Permission.*;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    GET_USERS,
                    CREATE_USERS,
                    UPDATE_USERS,
                    DELETE_USERS,

                    GET_COMPETITION,
                    CREATE_COMPETITION,
                    UPDATE_COMPETITION,
                    DELETE_COMPETITION,

                    GET_SPECIES,
                    CREATE_SPECIES,
                    UPDATE_SPECIES,
                    DELETE_SPECIES,

                    GET_PARTICIPATION,
                    CREATE_PARTICIPATION,
                    UPDATE_PARTICIPATION,
                    DELETE_PARTICIPATION,

                    GET_HUNT,

                    GET_TOPTHREE
            )
    ),
    MEMBER(
            Set.of(
                    GET_COMPETITION,
                    GET_SPECIES,
                    GET_PARTICIPATION_USER,
                    GET_TOPTHREE,

                    CREATE_PARTICIPATION,
                    UPDATE_PARTICIPATION,
                    DELETE_PARTICIPATION
            )
    ),
    JURY(
            Set.of(
                    GET_HUNT,
                    GET_COMPETITION,
                    GET_SPECIES,
                    GET_TOPTHREE,

                    GET_PARTICIPATION,
                    CREATE_PARTICIPATION,

                    CREATE_HUNT,
                    UPDATE_HUNT,
                    DELETE_HUNT
            )
    );

    @Getter
    private final Set<Permission> permissions;


    public List<SimpleGrantedAuthority> getAuthority() {
        var authorities = new ArrayList<>(getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
