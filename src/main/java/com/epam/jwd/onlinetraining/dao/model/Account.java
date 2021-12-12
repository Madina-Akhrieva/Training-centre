package com.epam.jwd.onlinetraining.dao.model;

import static com.epam.jwd.onlinetraining.dao.model.Role.STUDENT;

public class Account implements Entity {

    private final Long id;
    private final String password;
    private final String email;
    private final Role role;

    public Account(Long id, String password, String email) {
        this(id, password, email, STUDENT);
    }



    public Account(Long id, String password, String email, Role role) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Long getId() {
        return null;
    }
}

