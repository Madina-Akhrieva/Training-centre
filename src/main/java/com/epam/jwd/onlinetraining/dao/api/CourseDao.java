package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.core.CourseDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Course;

import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.dao.api public interface CourseDao
 * extends EntityDao<Course>
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface CourseDao extends EntityDao<Course> {
    Boolean delete(Long id);

    Optional<Long> findMentorIdByCourseID(Long id);

    static CourseDao instance() {
        return CourseDaoImpl.getInstance();
    }

    Course findCourseByCourseID(Long id);

    boolean update(Course entity, String title);


}
