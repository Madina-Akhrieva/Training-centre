package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.dao.model.Task;

public interface TaskDao<T extends Entity<K>, K> extends Dao<Task, Integer> {
}
