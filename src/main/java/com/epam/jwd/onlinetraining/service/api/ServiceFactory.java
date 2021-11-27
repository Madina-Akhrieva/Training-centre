package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.service.impl.SimpleServiceFactory;

public interface ServiceFactory {
    <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass);

    static SimpleServiceFactory simple(){
        return SimpleServiceFactory.INSTANCE;
    }
}
