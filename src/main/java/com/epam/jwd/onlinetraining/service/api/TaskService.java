package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService extends EntityService<Task> {
    Task findById(Long id);

    boolean update(Task course, String title);

    boolean delete(Long id);


    List<Task> findTasksByCourseId(long id);

    Optional<Task> addTaskToCourse(Task task, long id);
}

