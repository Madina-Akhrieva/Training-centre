package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityService<T extends Entity> {

    List<T> findAll();

    Optional<T> create(T entity);

    T add(T account);



}
