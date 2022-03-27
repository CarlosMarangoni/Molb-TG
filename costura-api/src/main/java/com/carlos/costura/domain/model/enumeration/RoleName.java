package com.carlos.costura.domain.model.enumeration;

public enum RoleName {
    ROLE_USER("user"),
    ROLE_CREATOR("creator"),
    ROLE_ADMIN("admin");

    private String name;

    RoleName(String name) {
        this.name = name;
    }
}
