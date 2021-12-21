package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Task;

public interface TaskService extends EntityService<Task> {
    Task findById(Long id);

    boolean update(Task course, String title);

    boolean delete(Long id);

}

