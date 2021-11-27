package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.impl.CourseDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.dao.model.Course;

public interface CourseDao extends EntityDao<Course> {

    static CourseDao instance(){
        return CourseDaoImpl.getInstance();
    }

}
