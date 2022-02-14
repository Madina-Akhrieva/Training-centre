package com.epam.jwd.onlinetraining.service.api;


import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongDescriptionException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;

import java.util.List;

/**
 * com.epam.jwd.onlinetraining.service.api public interface CourseService
 * extends EntityService<Course>
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface CourseService extends EntityService<Course> {
    Course findById(Long id);

    boolean update(Course course, String title) throws WrongDescriptionException, WrongTitleException, EmptyInputException;

    boolean delete(Long id);

}
