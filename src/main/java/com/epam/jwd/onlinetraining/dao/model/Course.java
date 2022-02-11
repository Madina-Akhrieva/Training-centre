package com.epam.jwd.onlinetraining.dao.model;

import java.util.List;
import java.util.Objects;

public class Course implements Entity {

    private Long id;
    private final String title;
    private final String learningLanguage;
    private final String description;
    private final Mentor mentor;

    public Course(Long id, String title, String learningLanguage, String description, Mentor mentor, List<User> users) {
        this.id = id;
        this.title = title;
        this.learningLanguage = learningLanguage;
        this.description = description;
        this.mentor = mentor;
    }

    public Course(Long id, String title, String learningLanguage, String description, Mentor mentor) {
        this.id = id;
        this.title = title;
        this.learningLanguage = learningLanguage;
        this.description = description;
        this.mentor = mentor;
    }

    public Course(Long id, String title, String learningLanguage, String description) {
        this(id, title, learningLanguage, description, null);
    }

    public Course(String title, String learningLanguage, String description) {
        this(null, title, learningLanguage, description);
    }

    public Course(Long courseId, String title) {
        this(courseId, title, null, null);

    }

    @Override
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLearningLanguage() {
        return learningLanguage;
    }

    public String getDescription() {
        return description;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course withMentor(Mentor mentor) {
        return new Course(id, title, learningLanguage, description, mentor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id)
                && Objects.equals(title, course.title)
                && Objects.equals(learningLanguage, course.learningLanguage)
                && Objects.equals(description, course.description)
                && Objects.equals(mentor, course.mentor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, learningLanguage, description, mentor);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", learningLanguage='" + learningLanguage + '\'' +
                ", description='" + description + '\'' +
                ", mentor=" + mentor +
                '}';
    }
}
