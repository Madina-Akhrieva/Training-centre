package com.epam.jwd.onlinetraining.dao.model;

import java.util.Objects;

public class User implements Entity {

    private Long id;
    private String phone;
    private String firstName;
    private String lastName;
    private Course course;

    public User(Long id, String phone, String firstName, String lastName) {
        this.id = id;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String phone, String firstName, String lastName) {
        this(null, phone, firstName, lastName);

    }

    public User(long userId, Course course, String firstName, String lastName) {
      this.id = userId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.course = course;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(phone, user.phone) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(course, user.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, firstName, lastName, course);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", course=" + course +
                '}';
    }
}
