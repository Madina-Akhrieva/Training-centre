package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.Entity;

import java.util.List;
import java.util.Optional;


public interface EntityDao<T extends Entity> {


    List<T> read();

    Optional<T> read(Long id);

    T create(T entity);


}
