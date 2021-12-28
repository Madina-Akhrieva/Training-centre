package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Entity;

import java.util.List;

public interface EntityService<T extends Entity> {

    List<T> findAll();

    List<T> findAll(long id);

    T create(T entity);

    T add(T account);
}
