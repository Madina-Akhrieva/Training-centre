package com.epam.jwd.onlinetraining.service.core;

import com.epam.jwd.onlinetraining.dao.api.AccountDao;
import com.epam.jwd.onlinetraining.dao.api.UserDao;
import com.epam.jwd.onlinetraining.dao.db.TransactionManager;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.api.UserService;
import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongFirstNameException;
import com.epam.jwd.onlinetraining.service.exception.WrongLastNameException;
import com.epam.jwd.onlinetraining.service.exception.WrongPhoneException;
import com.epam.jwd.onlinetraining.service.exception.WrongPhoneLength;
import com.epam.jwd.onlinetraining.service.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.service.core public class SimpleUserService
 * extends Object
 * implements UserService
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class SimpleUserService implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(SimpleUserService.class);
    private final UserValidator userValidator;
    private final UserDao userDao;
    private final AccountDao accountDao;

    public SimpleUserService(UserValidator userValidator, AccountDao accountDao, UserDao userDao, TransactionManager instance) {
        this.userValidator = userValidator;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAll(long id) {
        return null;
    }

    @Override
    public User create(User entity) {
        return userDao.create(entity);
    }

    @Override
    public User add(User account) {
        return null;
    }

    @Override
    public Optional<User> register(Long id, String phone, String firstname, String lastname) throws WrongFirstNameException, WrongPhoneException, WrongLastNameException, WrongPhoneLength, EmptyInputException {
        userValidator.validateFirstname(firstname);
        userValidator.validateLastname(lastname);
        userValidator.validatePhone(phone);
        final Optional<User> readUser = userDao.readUserByPhone(phone);
        if (readUser.isPresent()) {
            return Optional.empty();
        } else {
            User user = userDao.create(new User(id, phone, firstname, lastname));
            return Optional.ofNullable(user);
        }
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public boolean addCourseToUser(long courseId, long userId) {
        return userDao.addCourseToUser(courseId, userId);
    }

    @Override
    public boolean checkIfSubscriptionStringExist(long courseId, long userId) {
        return userDao.readSubscriptionByCourseIdAndUserId(courseId, userId);
    }

    @Override
    public List<Course> findAllCoursesByUserId(long userId) {
        return userDao.findAllCoursesByUserId(userId);
    }

    @Override
    public List<User> findStudentsByCourseId(long courseId) {
        return userDao.readStudentsByCourseId(courseId);
    }
}
