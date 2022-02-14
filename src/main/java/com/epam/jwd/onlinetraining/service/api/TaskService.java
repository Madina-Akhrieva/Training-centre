package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongFeedbackException;
import com.epam.jwd.onlinetraining.service.exception.WrongLinkException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;

import java.util.List;
import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.service.api public interface TaskService
 * extends EntityService<Task>
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface TaskService extends EntityService<Task> {
    Task findById(Long id);

    boolean delete(Long id);

    Optional<Task> addTaskToCourse(Task task, long id) throws WrongLinkException, WrongTitleException, EmptyInputException;

    boolean addTaskAnswerToUser(String answer, long courseUserId, long courseId, long idTask) throws WrongLinkException, EmptyInputException;

    List<Task> findAllTasksByCourseIdAndUserId(long courseId, long userId);

    boolean addFeedbackToAnswer(String answer, long userId, long taskId) throws WrongFeedbackException, EmptyInputException;

    Task findTasksByCourseIdTaskIdAndUserId(long userId, long courseId, long taskId);

    String findFeedbackByCourseIdUserIdAndTaskId(long courseId, long userId, long taskId);
}

