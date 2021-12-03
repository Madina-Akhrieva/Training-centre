package com.epam.jwd.onlinetraining.dao.model;

import java.util.Objects;

public class Mentor extends User {

    private int experience;
    private String position;


    public Mentor(Long id, String phone, String firstName, String lastName, int experience, String position) {
        super(id, phone, firstName, lastName);
        this.experience = experience;
        this.position = position;
    }

    public Mentor(String phone, String firstName, String lastName, String email, int experience, String position) {
        super(phone, firstName, lastName, email);
        this.experience = experience;
        this.position = position;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
