package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.api.AccountDao;
import com.epam.jwd.onlinetraining.dao.api.UserDao;
import com.epam.jwd.onlinetraining.dao.db.TransactionManager;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.api.UserService;

import java.util.List;
import java.util.Optional;

public class SimpleUserService implements UserService {

    private final UserDao userDao;
    private final AccountDao accountDao;
    private final TransactionManager transactionManager;

    public SimpleUserService(AccountDao accountDao, UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.transactionManager = transactionManager;
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
    public Optional<User> register(Long id, String phone, String firstname, String lastname) {
        if (phone == null || firstname == null || lastname==null) {
            return Optional.empty();
        }
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
    public List<String> findAllCoursesByUserId(long userId) {
        return userDao.findAllCoursesByUserId(userId);
    }
}
