package com.online.shop.enums;

public enum UserRole {
    ROLE_USER,
    ROLE_ADMIN;

    public String friendlyName() {
        switch (this) {
            case ROLE_USER:
                return "Client";
            case ROLE_ADMIN:
                return "Admin";
            default:
                return "Unknown";
        }
    }
}
