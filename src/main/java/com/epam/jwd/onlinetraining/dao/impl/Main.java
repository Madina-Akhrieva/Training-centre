package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.model.*;
import com.epam.jwd.onlinetraining.service.impl.CourseService;
import com.epam.jwd.onlinetraining.service.impl.UserService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //задача ментора состоит в том, чтобы оценивать работы студентов и давать отзывы на ыполненные задания
        //задача администратора составлять эти курсы и добавлять на курсы задания
        //задача пльзователя просто отправлять ответ наэти задания
        //у администратора будет кнопка создать курс, и кнопка добавить задания
        CourseService courseService = new CourseService();

        Course course = new Course(1,"title2", 6,"java", "description", null);
        courseService.save(course);

        TaskDaoImpl taskDao = new TaskDaoImpl();
        Task task = new Task(course.getId(), "description", 5,"answer", "feedback");

        Task task2 = new Task(course.getId(), "description", 6,"answer", "feedback");
        Task task3 = new Task(course.getId(), "description", 5,"answer", "feedback");
        Task task4 = new Task(course.getId(), "description", 5,"answer", "feedback");
        Task task5 = new Task(course.getId(), "description", 5,"answer", "feedback");
        Task task6 = new Task(course.getId(), "description", 5,"answer", "feedback");

        List<Task> tasksList =  new ArrayList<>();


        tasksList.add(task);
        tasksList.add(task2);
        tasksList.add(task3);
        tasksList.add(task4);
        tasksList.add(task5);
        tasksList.add(task6);

        for (Task taskElement: tasksList) {
            taskDao.save(taskElement);
        }
//        course.setTasksList(tasksList);
//
//        courseService.save(course);
    }
}
