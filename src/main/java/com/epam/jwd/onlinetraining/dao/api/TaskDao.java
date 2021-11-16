package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.AbstractEntity;

public interface TaskDao<T extends AbstractEntity<K>, K> {
    Boolean delete(K parameter);
}
