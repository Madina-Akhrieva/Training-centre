package com.epam.jwd.onlinetraining.dao.model;

import java.util.Arrays;
import java.util.List;

/**
 * com.epam.jwd.onlinetraining.dao.model public enum Role
 * extends Enum<Role>
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum Role {

    UNAUTHORIZED(0),
    STUDENT(1),
    ADMIN(2),
    MENTOR(3);

    private static final List<Role> ALL_AVAILABLE_ROLES = Arrays.asList(values());
    private int id;

    Role(int id) {
        this.id = id;
    }

    public static List<Role> valuesAsList() {
        return ALL_AVAILABLE_ROLES;
    }

    public static Role of(String name) {
        for (Role role : values()) {
            if (role.name().equalsIgnoreCase(name)) {
                return role;
            }
        }
        return STUDENT;
    }

    public int getId() {
        return id;
    }
}

