package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.service.dto.AbstractDto;

public interface Service<T extends AbstractDto<K>, K> {
    T create(T entity);
    T update(T entity);
    boolean delete(T entity);
    T getById(T entity);
    T getAll(T entity);
}
