package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.impl.TaskDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Task;

import java.util.List;

public interface TaskDao extends EntityDao<Task> {

    List<Task> findByTitle(String title);


    static TaskDao instance() {
        return TaskDaoImpl.getInstance();
    }
}
