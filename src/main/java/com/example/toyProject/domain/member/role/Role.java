package com.example.toyProject.domain.member.role;

public enum Role {
    PLAYER("player"), MANAGER("manager");

    private final String role;
    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
