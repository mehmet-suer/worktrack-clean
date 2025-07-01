package com.worktrack.user.domain;

import java.util.Objects;

public record User(UserId id, String username, String email, String fullName, Role role) {

    public User(UserId id, String username, String email, String fullName, Role role) {
        this.id = Objects.requireNonNull(id);
        this.username = Objects.requireNonNull(username);
        this.email = Objects.requireNonNull(email);
        this.fullName = fullName;
        this.role = Objects.requireNonNull(role);
    }

}