package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.api.MentorDao;
import com.epam.jwd.onlinetraining.dao.db.TransactionManager;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Mentor;

import com.epam.jwd.onlinetraining.service.api.CourseService;
import com.epam.jwd.onlinetraining.service.exception.WrongDescriptionException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;
import com.epam.jwd.onlinetraining.service.validator.CourseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class SimpleCourseService implements CourseService {
    private static final Logger LOGGER = LogManager.getLogger(SimpleCourseService.class);

    private final CourseValidator courseValidator;
    private final CourseDao courseDao;
    private final MentorDao mentorDao;
    private final TransactionManager transactionManager;

    public SimpleCourseService(CourseValidator courseValidator, CourseDao courseDao, MentorDao mentorDao, TransactionManager transactionManager) {
        this.courseValidator = courseValidator;
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

    @Override
    public List<Course> findAll(long id) {
        return null;
    }

    @Override
    public Course findById(Long id) {
        final Course course = courseDao.findCourseByCourseID(id);
        LOGGER.info("The course we got is: {}", course);
        return course;

    }

    @Override
    public boolean update(Course course, String title) throws WrongDescriptionException, WrongTitleException {
        courseValidator.validateTitle(course.getTitle());
        courseValidator.validateDescription(course.getDescription());
        LOGGER.info("The course we got is: {}", course);
        return courseDao.update(course, title);

    }

    @Override
    public boolean delete(Long id) {
        return courseDao.delete(id);
    }


    @Override
    public Course create(Course course) throws WrongDescriptionException, WrongTitleException {
        courseValidator.validateDescription(course.getDescription());
        courseValidator.validateTitle(course.getTitle());
        return courseDao.create(course);
    }

    @Override
    public Course add(Course account) {
        return null;
    }


}
