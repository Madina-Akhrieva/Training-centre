package com.epam.jwd.onlinetraining.dao.model;

public class User implements Entity {

    private Long id;
    private String phone;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
    }

    public User(Long id, String phone, String firstName, String lastName) {
        this.id = id;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String phone, String firstName, String lastName, String email) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public Long getId() {
        return id;
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

}
