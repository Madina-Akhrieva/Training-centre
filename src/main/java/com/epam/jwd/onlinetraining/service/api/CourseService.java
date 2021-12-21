package com.epam.jwd.onlinetraining.service.api;


import com.epam.jwd.onlinetraining.dao.model.Course;

public interface CourseService extends EntityService<Course> {
    Course findById(Long id);

    boolean update(Course course, String title);

    boolean delete(Long id);

}
