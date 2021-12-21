package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.api.MentorDao;
import com.epam.jwd.onlinetraining.dao.api.TaskDao;
import com.epam.jwd.onlinetraining.dao.db.TransactionManager;
import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.api.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class SimpleTaskService implements TaskService {

    private static final Logger LOGGER = LogManager.getLogger(SimpleCourseService.class);

    private final CourseDao courseDao;
    private final TaskDao taskDao;
    private final TransactionManager transactionManager;

    public SimpleTaskService(CourseDao courseDao, TaskDao taskDao, TransactionManager transactionManager) {
        this.courseDao = courseDao;
        this.taskDao = taskDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Optional<Task> create(Task entity) {
        return Optional.empty();
    }

    @Override
    public Task add(Task account) {
        return null;
    }

    @Override
    public Task findById(Long id) {
        return null;
    }

    @Override
    public boolean update(Task course, String title) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
