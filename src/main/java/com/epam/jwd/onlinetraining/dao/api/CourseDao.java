package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.AbstractEntity;
import com.epam.jwd.onlinetraining.dao.model.Course;

public interface CourseDao<T extends AbstractEntity<K>, K>{
    Boolean delete(K id);
}
