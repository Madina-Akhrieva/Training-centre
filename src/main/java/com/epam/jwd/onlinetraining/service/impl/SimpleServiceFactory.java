package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.api.MentorDao;
import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.service.api.EntityService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class SimpleServiceFactory implements ServiceFactory {

    public static final SimpleServiceFactory INSTANCE = new SimpleServiceFactory();

    private static final String SERVICE_NOT_FOUND = "Couldn't create service %s";

    Map<Class<?>, EntityService<?>> serviceByEntity = new ConcurrentHashMap();

    @Override
    @SuppressWarnings("unckecked")
    public <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass) {

        return (EntityService<T>) serviceByEntity
                .computeIfAbsent(modelClass, createServiceFromClass());
    }

    //if server wasn't found and will create servicese
    private Function<Class<?>, EntityService<?>> createServiceFromClass() {
        return clazz -> {
            final String className = clazz.getSimpleName();
            switch (className) {
                case "Course":
                    return new CourseService(CourseDao.instance(), MentorDao.instance());
                default:
                    throw new IllegalArgumentException(String.format(SERVICE_NOT_FOUND, className));

            }
        };
    }

}
