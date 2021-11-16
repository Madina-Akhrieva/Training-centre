package com.epam.jwd.onlinetraining.dao.model;

import java.util.Objects;

public class Task extends AbstractEntity<Integer> {
    private Integer courseId;
    private String description;
    private Integer taskNumber;
    private String taskAnswer;
    private String feedback;
    private Course course;

    public Task() {
    }

    public Task(Integer courseId, String description, Integer taskNumber,
                String taskAnswer, String feedback) {
        this.courseId = courseId;
        this.description = description;
        this.taskNumber = taskNumber;
        this.taskAnswer = taskAnswer;
        this.feedback = feedback;
    }

    public Task(Integer id, Integer courseId, String description, Integer taskNumber,
                String taskAnswer, String feedback) {
        super(id);
        this.courseId = courseId;
        this.description = description;
        this.taskNumber = taskNumber;
        this.taskAnswer = taskAnswer;
        this.feedback = feedback;
    }

    public String getDescription() {
        return description;
    }


    public Integer getTaskNumber() {
        return taskNumber;
    }

    public String getTaskAnswer() {
        return taskAnswer;
    }

    public String getFeedback() {
        return feedback;
    }

    public Integer getCourseId() { return  course.getId(); }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(courseId, task.courseId)
                && Objects.equals(description, task.description)
                && Objects.equals(taskNumber, task.taskNumber)
                && Objects.equals(taskAnswer, task.taskAnswer)
                && Objects.equals(feedback, task.feedback)
                && Objects.equals(course, task.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, description, taskNumber, taskAnswer, feedback, course);
    }
}
