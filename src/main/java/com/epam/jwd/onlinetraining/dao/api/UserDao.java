package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.core.UserDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.User;

import java.util.List;
import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.dao.api public interface UserDao
 * extends EntityDao<User>
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface UserDao extends EntityDao<User> {

    static UserDao instance() {
        return UserDaoImpl.getInstance();
    }

    Optional<User> readUserByPhone(String phone);

    User findById(long id);

    boolean addCourseToUser(long courseId, long userId);

    boolean readSubscriptionByCourseIdAndUserId(long courseId, long userId);

    List<Course> findAllCoursesByUserId(long userId);

    List<User> readStudentsByCourseId(long courseId);
}
