package com.epam.jwd.onlinetraining.dao.model;

public class Account implements Entity {
    private Long id;
    private int roleId;
    private String password;
    private String email;

    public Account() {

    }

    public Account(int roleId, String password, String email) {
        this.roleId = roleId;
        this.password = password;
        this.email = email;
    }


    public Account(Long id, int roleId, String password, String email) {
        this.id = id;
        this.roleId = roleId;
        this.password = password;
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
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

