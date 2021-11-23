package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.Entity;

import java.util.List;
import java.util.Optional;


public interface Dao<T extends Entity<K>, K> {
    T save(T entity);

    Boolean update(T entity);

    T findById(K id);

    Boolean delete(K id);

    List<T> read();

    Optional<T> read(K id);

}
