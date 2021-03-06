package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.core.TaskDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Task;

import java.util.List;
import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.dao.api public interface TaskDao
 * extends EntityDao<Task>
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface TaskDao extends EntityDao<Task> {

    static TaskDao instance() {
        return TaskDaoImpl.getInstance();
    }

    List<Task> findTasksByCourseId(long id);

    Optional<Task> addTaskToCourse(Task task, long courseId);

    boolean addTaskToAnswer(String answer, long courseUserId, long courseId, long idTask);

    List<Task> readAllTasksByCourseIdAndUserId(long courseId, long userId);

    boolean createFeedbackToAnswer(String answer, long userId, long taskId);

    Task readTasksByCourseIdTaskIdAndUserId(long userId, long courseId, long taskId);

    String readFeedbackByCourseIdUserIdAndTaskId(long courseId, long userId, long taskId);
}
