package com.epam.jwd.onlinetraining.dao.model;

public class Course implements Entity {

    private Long id;
    private final String title;
    private final Integer amountOfTasks;
    private final String learningLanguage;
    private final String description;
    private final Mentor mentor;


    public Course(Long id, String title, Integer amountOfTasks, String learningLanguage, String description, Mentor mentor) {
        this.id = id;
        this.title = title;
        this.amountOfTasks = amountOfTasks;
        this.learningLanguage = learningLanguage;
        this.description = description;
        this.mentor = mentor;
    }

    public Course(String title, Integer amountOfTasks, String learningLanguage, String description) {
        this(null, title, amountOfTasks, learningLanguage, description, null);
    }

    @Override
    public Long getId() {
        return null;
    }


    public String getTitle() {
        return title;
    }


    public Integer getAmountOfTasks() {
        return amountOfTasks;
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

    public Course withMentor(Mentor mentor) {
        return new Course(id, title, amountOfTasks, learningLanguage, description, mentor);
    }

}
