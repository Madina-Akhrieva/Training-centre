package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongFirstNameException;
import com.epam.jwd.onlinetraining.service.exception.WrongLastNameException;
import com.epam.jwd.onlinetraining.service.exception.WrongPhoneException;
import com.epam.jwd.onlinetraining.service.exception.WrongPhoneLength;

import java.util.List;
import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.service.api public interface UserService
 * extends EntityService<User>
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface UserService extends EntityService<User> {
    Optional<User> register(Long id, String phone, String firstname, String lastname) throws WrongFirstNameException, WrongPhoneException, WrongLastNameException, WrongPhoneLength, EmptyInputException;

    User findById(long id);

    boolean addCourseToUser(long courseId, long userId);

    boolean checkIfSubscriptionStringExist(long courseId, long userId);

    List<Course> findAllCoursesByUserId(long userId);

    List<User> findStudentsByCourseId(long courseId);
}
