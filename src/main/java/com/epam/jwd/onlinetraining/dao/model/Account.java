package com.epam.jwd.onlinetraining.dao.model;

import java.util.Objects;

import static com.epam.jwd.onlinetraining.dao.model.Role.STUDENT;
import static com.epam.jwd.onlinetraining.dao.model.Role.valueOf;

/**
 * com.epam.jwd.onlinetraining.dao.model public class Account
 * extends Object
 * implements Entity
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class Account implements Entity {

    private Long id;
    private final String password;
    private final String email;
    private Role role;
    private User user;

    public Account(Long id, String password, String email) {
        this(id, password, email, STUDENT);
    }


    public Account(Long id, String password, String email, Role role) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Account(String password, String email) {
        this.password = password;
        this.email = email;
    }


    @Override
    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id)
                && Objects.equals(password, account.password)
                && Objects.equals(email, account.email)
                && role == account.role
                && Objects.equals(user, account.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, email, role, user);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", user=" + user +
                '}';
    }
}

