package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
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

    private final TaskDao taskDao;
    private final CourseDao courseDao;
    private final TransactionManager transactionManager;

    public SimpleTaskService(TaskDao taskDao, CourseDao courseDao, TransactionManager transactionManager) {
        this.courseDao = courseDao;
        this.taskDao = taskDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public List<Task> findAll(long id) {
        return taskDao.findTasksByCourseId(id);
    }

    @Override
    public Task create(Task entity) {
        return null;
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

    @Override
    public List<Task> findTasksByCourseId(long id) {
        return taskDao.findTasksByCourseId(id);
    }

    @Override
    public Optional<Task> addTaskToCourse(Task task, long courseId) {
        return taskDao.addTaskToCourse(task, courseId);
    }

    @Override
    public boolean addTaskAnswerToUser(String answer, long courseUserId, long courseId, long idTask) {
        return taskDao.addTaskToAnswer(answer, courseUserId, courseId, idTask);
    }

    @Override
    public List<Task> findAllTasksByCourseIdAndUserId(long courseId, long userId) {
        return taskDao.readAllTasksByCourseIdAndUserId(courseId, userId);
    }

    @Override
    public boolean addFeedbackToAnswer(String answer, long userId, long taskId) {
        return taskDao.createFeedbackToAnswer(answer, userId, taskId);
    }

    @Override
    public Task findTasksByCourseIdTaskIdAndUserId(long userId, long courseId, long taskId) {
        return taskDao.readTasksByCourseIdTaskIdAndUserId(userId, courseId, taskId);
    }

    @Override
    public String findFeedbackByCourseIdUserIdAndTaskId(long courseId, long userId, long taskId) {
        return taskDao.readFeedbackByCourseIdUserIdAndTaskId(courseId, userId, taskId);
    }


}