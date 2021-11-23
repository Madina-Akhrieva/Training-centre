package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.dao.model.Course;

public interface CourseDao<T extends Entity<K>, K> extends Dao<Course, Integer>{
    K findByTitle(String title);
    K findByLanguage(String language);

}
