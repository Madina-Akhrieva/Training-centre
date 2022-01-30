package com.epam.jwd.onlinetraining.dao.model;

import static com.epam.jwd.onlinetraining.dao.model.Role.STUDENT;
import static com.epam.jwd.onlinetraining.dao.model.Role.valueOf;

public class Account implements Entity {

    private Long id;
    private final String password;
    private final String email;
    private Role role;
    private User user;

    public Account(Long id, String password, String email) {
        this(id, password, email, STUDENT );
    }


    public Account(Long id, String password, String email, Role role ) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Account(String password, String email) {
        this.password = password;
        this.email = email;
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
        return id;
    }

    public Account withPassword(String password) {
        return new Account(id, email, password);
    }

    public void setId(Long id) {
        this.id = id;
    }
}

