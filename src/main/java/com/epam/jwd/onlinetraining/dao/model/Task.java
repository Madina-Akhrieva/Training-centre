package com.epam.jwd.onlinetraining.dao.model;

import java.util.Objects;

public class Task implements Entity {

    private Long id;
    private Long courseId;
    private final String title;
    private final String description;
    private final String task_answer;
    private final String feedback;

    public Task(Long id,Long courseId, String title, String description, String task_answer, String feedback) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.task_answer = task_answer;
        this.feedback = feedback;
    }

    public Task(String title,Long courseId, String description, String task_answer, String feedback) {
        this(null,null,  title, description, task_answer, feedback);
    }

    public Task(Long courseId,String title, String description) {
        this(null,courseId,  title, description, null, null);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTask_answer() {
        return task_answer;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id)
                && Objects.equals(title, task.title)
                && Objects.equals(description, task.description)
                && Objects.equals(task_answer, task.task_answer)
                && Objects.equals(feedback, task.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, task_answer, feedback);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", task_answer='" + task_answer + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
