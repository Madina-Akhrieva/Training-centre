package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Entity;

import java.util.List;

public interface EntityService<T extends Entity> {

    List<T> findAll();




}
