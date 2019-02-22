package com.mraihaniqbal.bootcamp.springrms.enums;

public enum  Authority {
    ROLE_ADMIN("Admin"),
    ROLE_USER("User");

    private final String displayName;

    Authority(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
