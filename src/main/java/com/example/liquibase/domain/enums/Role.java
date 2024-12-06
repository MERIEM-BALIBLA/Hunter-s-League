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
                    MANAGE_USER,

                    MANAGE_COMPETITION,

                    MANAGE_SPECIES,

                    MANAGE_PARTICIPATION,

                    MANAGE_HUNT,

                    CAN_VIEW_RANKINGS
            )
    ),
    MEMBER(
            Set.of(
                    CAN_VIEW_COMPETITIONS,
                    CAN_VIEW_SPECIES,
                    GET_PARTICIPATION_USER,
                    CAN_VIEW_RANKINGS,

                    MANAGE_PARTICIPATION
            )
    ),

    JURY(
            Set.of(
                    CAN_VIEW_COMPETITIONS,
                    CAN_VIEW_SPECIES,
                    GET_PARTICIPATION_USER,
                    CAN_VIEW_RANKINGS,
                    MANAGE_PARTICIPATION,
                    MANAGE_HUNT
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
