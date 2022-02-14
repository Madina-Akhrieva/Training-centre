package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.Entity;

import java.util.List;
import java.util.Optional;


/**
 * com.epam.jwd.onlinetraining.dao.api public interface EntityDao<T extends Entity>
 *
 * @param <T>
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface EntityDao<T extends Entity> {

    List<T> read();

    Optional<T> read(Long id);

    T create(T entity);


}
