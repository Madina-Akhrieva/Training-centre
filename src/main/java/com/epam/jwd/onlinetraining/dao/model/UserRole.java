package com.epam.jwd.onlinetraining.dao.model;

import java.util.Arrays;
import java.util.Optional;

public enum UserRole {

    ADMIN("admin", 1),
    STUDENT("student", 2),
    CLIENT("client", 3);

    private final String name;
    private final Integer id;

    UserRole(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public static Optional<UserRole> getRoleById(Integer id) {
        return Arrays.stream(values())
                .filter(role -> role.id == id)
                .findAny();
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}

