package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.impl.CourseDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao extends EntityDao<Course> {

    List<Course> findByTitle(String title);

    List<Course> findByLanguage(String language);

    Optional<Long> findMentorIdByCourseID(Long id);

    static CourseDao instance() {

        return CourseDaoImpl.getInstance();
    }

}
