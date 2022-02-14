package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongDescriptionException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;

import java.util.List;

/**
 * com.epam.jwd.onlinetraining.service.api public interface EntityService<T extends Entity>
 *
 * @param <T>
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface EntityService<T extends Entity> {

    List<T> findAll();

    List<T> findAll(long id);

    T create(T entity) throws WrongDescriptionException, WrongTitleException, EmptyInputException;

    T add(T account);
}
