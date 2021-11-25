package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.service.dto.AbstractDto;

import java.util.List;

public interface Service<T extends Entity> {

    List<T> findAll(T entity);
}
