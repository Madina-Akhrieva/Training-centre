package com.epam.jwd.onlinetraining.dao.model;

import java.util.Objects;

public class Task implements Entity {

    private Long id;
    private Long courseId;
    private final String title;
    private final String description;
    private final String taskAnswer;
    private final String feedback;

    public Task(Long id, Long courseId, String title, String description, String taskAnswer, String feedback) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.taskAnswer = taskAnswer;
        this.feedback = feedback;
    }

    public Task(String title, Long courseId, String description, String taskAnswer, String feedback) {
        this(null, null, title, description, taskAnswer, feedback);
    }

    public Task(Long courseId, String title, String description) {
        this(null, courseId, title, description, null, null);
    }

    public Long getCourseId() {
        return courseId;
    }

    @Override
    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getTaskAnswer() {
        return taskAnswer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id)
                && Objects.equals(courseId, task.courseId)
                && Objects.equals(title, task.title)
                && Objects.equals(description, task.description)
                && Objects.equals(taskAnswer, task.taskAnswer)
                && Objects.equals(feedback, task.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, title, description, taskAnswer, feedback);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskAnswer='" + taskAnswer + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
