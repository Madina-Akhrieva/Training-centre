package com.epam.jwd.onlinetraining.dao.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course extends AbstractEntity<Integer>{
    private Integer courseId;
    private Integer mentorId;
    private String title;
    private Integer amountOfTasks;
    private String learningLanguage;
    private String description;
    private List<Task> tasksList = new ArrayList<>();

    public Course() {
    }

    public Course(Integer id, Integer courseId, Integer mentorId, String title,
                  Integer amountOfTasks, String learningLanguage, String description,
                  List<Task> tasksList) {
        super(id);
        this.courseId = courseId;
        this.mentorId = mentorId;
        this.title = title;
        this.amountOfTasks = amountOfTasks;
        this.learningLanguage = learningLanguage;
        this.description = description;
        this.tasksList = tasksList;
    }

    public Course(Integer mentorId, String title, Integer amountOfTasks,
                  String learningLanguage, String description, List<Task> tasksList) {

        this.mentorId = mentorId;
        this.title = title;
        this.amountOfTasks = amountOfTasks;
        this.learningLanguage = learningLanguage;
        this.description = description;
        this.tasksList = tasksList;
    }

    public Course(Integer mentorId, String title, Integer amountOfTasks,
                  String learningLanguage, String description) {
        this(mentorId,title,amountOfTasks,learningLanguage,description,null);
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAmountOfTasks() {
        return amountOfTasks;
    }

    public void setAmountOfTasks(Integer amountOfTasks) {
        this.amountOfTasks = amountOfTasks;
    }

    public String getLearningLanguage() {
        return learningLanguage;
    }

    public void setLearningLanguage(String learningLanguage) {
        this.learningLanguage = learningLanguage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Task> getTasksList() {
        return tasksList;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setMentorId(Integer mentorId) {
        this.mentorId = mentorId;
    }

    public void setTasksList(List<Task> tasksList) {
        this.tasksList = tasksList;
        for (Task task : tasksList) {
            task.setCourse(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId)
                && Objects.equals(mentorId, course.mentorId)
                && Objects.equals(title, course.title)
                && Objects.equals(amountOfTasks, course.amountOfTasks)
                && Objects.equals(learningLanguage, course.learningLanguage)
                && Objects.equals(description, course.description)
                && Objects.equals(tasksList, course.tasksList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, mentorId, title, amountOfTasks,
                learningLanguage, description, tasksList);
    }
}
