package com.epam.jwd.onlinetraining.service.core;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.epam.jwd.onlinetraining.dao.api.AccountDao;
import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.api.MentorDao;
import com.epam.jwd.onlinetraining.dao.api.TaskDao;
import com.epam.jwd.onlinetraining.dao.api.UserDao;
import com.epam.jwd.onlinetraining.dao.db.TransactionManager;
import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.service.api.EntityService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.validator.AccountValidator;
import com.epam.jwd.onlinetraining.service.validator.CourseValidator;
import com.epam.jwd.onlinetraining.service.validator.FeedbackValidator;
import com.epam.jwd.onlinetraining.service.validator.TaskValidator;
import com.epam.jwd.onlinetraining.service.validator.UserValidator;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * com.epam.jwd.onlinetraining.service.core public class SimpleServiceFactory
 * extends Object
 * implements ServiceFactory
 *
 *  @author Madina Akhrieva
 *  @version 1.0
 */
public class SimpleServiceFactory implements ServiceFactory {

    public static final SimpleServiceFactory INSTANCE = new SimpleServiceFactory();

    private static final String SERVICE_NOT_FOUND = "Couldn't create service %s";

    Map<Class<?>, EntityService<?>> serviceByEntity = new ConcurrentHashMap();

    @Override
    @SuppressWarnings("unckecked")
    public <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass) {

        return (EntityService<T>) serviceByEntity
                .computeIfAbsent(modelClass, createServiceFromClass());
    }

    private Function<Class<?>, EntityService<?>> createServiceFromClass() {
        return clazz -> {
            final String className = clazz.getSimpleName();
            switch (className) {
                case "Course":
                    return new SimpleCourseService(CourseValidator.getInstance(), CourseDao.instance(), MentorDao.instance(), TransactionManager.instance());
                case "Task":
                    return new SimpleTaskService(TaskDao.instance(), TaskValidator.getInstance(), FeedbackValidator.getInstance(), CourseDao.instance(), TransactionManager.instance());
                case "User":
                    return new SimpleUserService(UserValidator.getInstance(), AccountDao.instance(), UserDao.instance(), TransactionManager.instance());
                case "Account":
                    return new SimpleAccountService(AccountValidator.getInstance(), AccountDao.instance(), UserDao.instance(), BCrypt.withDefaults(), BCrypt.verifyer());
                default:
                    throw new IllegalArgumentException(String.format(SERVICE_NOT_FOUND, className));

            }
        };
    }

}
