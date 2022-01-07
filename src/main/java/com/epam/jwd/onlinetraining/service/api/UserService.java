package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends EntityService<User>{
    Optional<User> register(Long id, String phone, String firstname, String lastname);

    User findById(long id);

    boolean addCourseToUser(long courseId, long userId);

    boolean checkIfSubscriptionStringExist(long courseId, long userId);

    List<Course> findAllCoursesByUserId(long userId);

    List<User> findStudentsByCourseId(long courseId);
}
