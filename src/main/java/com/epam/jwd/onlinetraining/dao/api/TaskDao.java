package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.AbstractEntity;
import com.epam.jwd.onlinetraining.dao.model.Task;

public interface TaskDao<T extends AbstractEntity<K>, K> extends Dao<Task, Integer> {
    Boolean delete(K parameter);
}
