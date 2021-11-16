package com.epam.jwd.onlinetraining.dao.model;

import java.util.Objects;

public class Mentor extends AbstractEntity<Integer>{
    private String universityTitle;
    private Integer amountOfWorkExperience;
    private Integer universityDegreeAvailability;

    public Mentor() {
    }

    public Mentor(String universityTitle, Integer amountOfWorkExperience, Integer universityDegreeAvailability) {
        this.universityTitle = universityTitle;
        this.amountOfWorkExperience = amountOfWorkExperience;
        this.universityDegreeAvailability = universityDegreeAvailability;
    }

    public String getUniversityTitle() {
        return universityTitle;
    }

    public void setUniversityTitle(String universityTitle) {
        this.universityTitle = universityTitle;
    }

    public Integer getAmountOfWorkingExperience() {
        return amountOfWorkExperience;
    }

    public void setAmountOfWorkingExperience(Integer amountOfWorkingExperience) {
        this.amountOfWorkExperience = amountOfWorkingExperience;
    }

    public Integer getUniversityDegreeAvailability() {
        return universityDegreeAvailability;
    }

    public void setUniversityDegreeAvailability(Integer universityDegreeAvailability) {
        this.universityDegreeAvailability = universityDegreeAvailability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor mentor = (Mentor) o;
        return Objects.equals(universityTitle, mentor.universityTitle)
                && Objects.equals(amountOfWorkExperience, mentor.amountOfWorkExperience)
                && Objects.equals(universityDegreeAvailability, mentor.universityDegreeAvailability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityTitle, amountOfWorkExperience, universityDegreeAvailability);
    }
}
