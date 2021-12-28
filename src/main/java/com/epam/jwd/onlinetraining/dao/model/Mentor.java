package com.epam.jwd.onlinetraining.dao.model;

import java.util.Objects;

public class Mentor extends User {

    private int experience;
    private String position;
    private String pen_name;


    public Mentor(Long id, String phone, String firstName, String lastName, int experience, String position, String pen_name) {
        super(id, phone, firstName, lastName);
        this.experience = experience;
        this.position = position;
        this.pen_name = pen_name;
    }



    public Mentor(Long id, int experience, String position, String pen_name) {
        super(id, null, null, null);
        this.experience = experience;
        this.position = position;
        this.pen_name = pen_name;
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

    public String getPen_name() {
        return pen_name;
    }

    public void setPen_name(String pen_name) {
        this.pen_name = pen_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor mentor = (Mentor) o;
        return experience == mentor.experience && Objects.equals(position, mentor.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experience, position);
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "experience=" + experience +
                ", position='" + position + '\'' +
                '}';
    }
}
