package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.impl.CourseDaoImpl;
import com.epam.jwd.onlinetraining.dao.impl.TaskDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.api.Service;
import com.epam.jwd.onlinetraining.service.dto.UserDto;

import java.util.ArrayList;
import java.util.List;


public class CourseService implements Service<UserDto, Integer> {
    TaskDaoImpl taskDaoImpl = new TaskDaoImpl();
    CourseDaoImpl courseDaoImpl = new CourseDaoImpl();
    List<Task> tasksList = new ArrayList<>();

//    public Course save(Course course) {
//        try {
//            for (Task taskFromTaskList : course.getTasksList()) {
//                Task task = taskDaoImpl.save(taskFromTaskList);
//                tasksList.add(task);
//
//            }
//            course.setTasksList(tasksList);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        return courseDaoImpl.save(course);
//    }

    @Override
    public UserDto create(UserDto entity) {
        return null;
    }

    @Override
    public UserDto update(UserDto entity) {
        return null;
    }

    @Override
    public boolean delete(UserDto entity) {
        return false;
    }

    @Override
    public UserDto getById(UserDto entity) {
        return null;
    }

    @Override
    public UserDto getAll(UserDto entity) {
        return null;
    }
}
