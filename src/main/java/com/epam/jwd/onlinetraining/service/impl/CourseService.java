package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.api.MentorDao;
import com.epam.jwd.onlinetraining.dao.db.TransactionManager;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Mentor;

import com.epam.jwd.onlinetraining.service.api.EntityService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CourseService implements EntityService<Course> {

    private final CourseDao courseDao;
    private final MentorDao mentorDao;
    private final TransactionManager transactionManager;

    public CourseService(CourseDao courseDao, MentorDao mentorDao, TransactionManager transactionManager) {
        this.courseDao = courseDao;
        this.mentorDao = mentorDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Course> findAll() {
//        transactionManager.initTransaction();
        List<Course> courses = courseDao.read()
                .stream()
                .map(course -> {
                    final Long courseMentorId = courseDao.findMentorIdByCourseID(course.getId()).orElse(null);
                    final Optional<Mentor> courseMentor = mentorDao.read(courseMentorId);
                    return course.withMentor(courseMentor.orElse(null));
                })
                .collect(Collectors.toList());
//        transactionManager.commitTransaction();
        return courses;
    }

}
