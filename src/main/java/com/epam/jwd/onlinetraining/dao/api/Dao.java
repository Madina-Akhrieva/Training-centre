package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.AbstractEntity;


public interface Dao<T extends AbstractEntity<K>, K> {
    T save(T entity);
    Boolean update(T entity);
}
