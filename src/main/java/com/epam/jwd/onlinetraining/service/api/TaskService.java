package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.exception.WrongFeedbackException;
import com.epam.jwd.onlinetraining.service.exception.WrongLinkException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;

import java.util.List;
import java.util.Optional;

public interface TaskService extends EntityService<Task> {
    Task findById(Long id);

    boolean update(Task course, String title);

    boolean delete(Long id);

    List<Task> findTasksByCourseId(long id);

    Optional<Task> addTaskToCourse(Task task, long id) throws WrongLinkException, WrongTitleException;

    boolean addTaskAnswerToUser(String answer, long courseUserId, long courseId, long idTask) throws WrongLinkException;

    List<Task> findAllTasksByCourseIdAndUserId(long courseId, long userId);

    boolean addFeedbackToAnswer(String answer, long userId, long taskId) throws WrongFeedbackException;

    Task findTasksByCourseIdTaskIdAndUserId(long userId, long courseId, long taskId);

    String findFeedbackByCourseIdUserIdAndTaskId(long courseId, long userId, long taskId);
}

