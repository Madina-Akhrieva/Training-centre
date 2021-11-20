package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.model.*;
import com.epam.jwd.onlinetraining.service.impl.CourseService;
import com.epam.jwd.onlinetraining.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        //задача ментора состоит в том, чтобы оценивать работы студентов и давать отзывы на ыполненные задания
        //задача администратора составлять эти курсы и добавлять на курсы задания
        //задача пльзователя просто отправлять ответ наэти задания
        //у администратора будет кнопка создать курс, и кнопка добавить задания

        CourseService courseService = new CourseService();
        //тут адмминимтратор заходит на страничку и указывает что он хочет создать какой-то курс  и говорит по какой тебе будет этот курс
        //

        CourseDaoImpl courseDao = new CourseDaoImpl();
        Course course = new Course(1,"new title", 6,"java", "description", null);
        Course course1 = courseDao.save(course);

        //здесь у нас сохранен курс без тасков
        //заходит ментор, видит таск

        Task task = new Task(course1.getId(), "new task", 5,"answer", "feedback");
        TaskDaoImpl taskDao = new TaskDaoImpl();
        taskDao.save(task);

        taskDao.save(task);


//        TaskDaoImpl taskDao = new TaskDaoImpl();
//        Task task2 = new Task(course.getId(), "description", 6,"answer", "feedback");
//        Task task3 = new Task(course.getId(), "description", 5,"answer", "feedback");
//        Task task4 = new Task(course.getId(), "description", 5,"answer", "feedback");
//        Task task5 = new Task(course.getId(), "description", 5,"answer", "feedback");
//        Task task6 = new Task(course.getId(), "description", 5,"answer", "feedback");
//        List<Task> tasksList =  new ArrayList<>();
//

        //ментор может создавать курс
        //изначаль когда создается курс у него не никаких заданий, задания модет добавлять  ментор
        //поэтому изначально у таска list  с курсами пустой а заполняется когда добавляется новое задание
        //у таска при добавлении на курс будет id куоса на который он был добавлен
        //


//
//        tasksList.add(task);
//        tasksList.add(task2);
//        tasksList.add(task3);
//        tasksList.add(task4);
//        tasksList.add(task5);
//        tasksList.add(task6);
//
//        for (Task taskElement: tasksList) {
//            taskDao.save(taskElement);
//        }
//        course.setTasksList(tasksList);
//
//        courseService.save(course);
        ConnectionPoolImpl connectionPool = new ConnectionPoolImpl();
        connectionPool.shutdown();
    }
}