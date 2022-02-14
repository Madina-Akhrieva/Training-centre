package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.core.SimpleServiceFactory;

/**
 * com.epam.jwd.onlinetraining.service.api public interface ServiceFactory
 *
 *  @author Madina Akhrieva
 *  @version 1.0
 */
public interface ServiceFactory {

    <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass);

    static SimpleServiceFactory simple() {
        return SimpleServiceFactory.INSTANCE;
    }

    default AccountService accountService() {
        return (AccountService) serviceFor(Account.class);
    }

    default UserService userService() {
        return (UserService) serviceFor(User.class);
    }

    default CourseService courseService() {
        return (CourseService) serviceFor(Course.class);
    }

    default TaskService taskService() {
        return (TaskService) serviceFor(Task.class);
    }
}