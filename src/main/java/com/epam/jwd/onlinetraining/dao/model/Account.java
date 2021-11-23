package com.epam.jwd.onlinetraining.dao.model;

import java.util.Objects;

public class Account extends Entity<Integer> {
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

    public Account(Integer id, int roleId, String password, String email) {
        super(id);
        this.roleId = roleId;
        this.password = password;
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return roleId == account.roleId
                && Objects.equals(password, account.password)
                && Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, password, email);
    }


}

