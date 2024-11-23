package com.university.uch_university.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    ADMIN, DIRECTOR, MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
