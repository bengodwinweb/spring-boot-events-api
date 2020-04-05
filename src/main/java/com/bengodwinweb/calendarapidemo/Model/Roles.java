package com.bengodwinweb.calendarapidemo.Model;

public enum Roles {
    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String get() {
        return role;
    }
}
