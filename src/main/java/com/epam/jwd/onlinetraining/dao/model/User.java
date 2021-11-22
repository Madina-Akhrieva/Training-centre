package com.epam.jwd.onlinetraining.dao.model;

import java.util.Date;
import java.util.Objects;

public class User extends AbstractEntity<Integer> {

    private Integer roleId;
    private String phone;
    private String firstName;
    private String lastName;
    private Account account;
    private UserRole userRole;

    public Integer getId() {
        return account.getId();
    }

    public User(Integer id, Integer roleId,
                String phone,
                String firstName, String lastName,
                Account account, UserRole userRole) {
        super(id);
        this.roleId = roleId;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
        this.userRole = userRole;
    }


    public User(Integer roleId, String phone,
                String firstName, String lastName, Account account,
                UserRole userRole) {
        this(null, roleId, phone, firstName, lastName, account, userRole);
    }

    public User(Integer roleId, String phone,
                String firstName, String lastName) {

        this(null, roleId, phone, firstName, lastName, null, null);
    }

    //todo:comment
    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(roleId, user.roleId) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(account, user.account) && userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, phone, firstName, lastName, account, userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "roleId=" + roleId +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", account=" + account +
                ", userRole=" + userRole +
                '}';
    }
}
