package com.epam.jwd.onlinetraining.dao.model;

import java.util.Objects;

/**
 * com.epam.jwd.onlinetraining.dao.model public class Mentor
 * extends User
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
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

    public String getPen_name() {
        return pen_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mentor mentor = (Mentor) o;
        return experience == mentor.experience
                && Objects.equals(position, mentor.position)
                && Objects.equals(pen_name, mentor.pen_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experience, position, pen_name);
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "experience=" + experience +
                ", position='" + position + '\'' +
                ", pen_name='" + pen_name + '\'' +
                '}';
    }
}
