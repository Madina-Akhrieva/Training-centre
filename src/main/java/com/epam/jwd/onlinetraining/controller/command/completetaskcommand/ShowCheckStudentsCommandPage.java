package com.epam.jwd.onlinetraining.controller.command.completetaskcommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.api.CourseService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.UserService;

import java.util.List;

public enum ShowCheckStudentsCommandPage implements Command {
    INSTANCE(ServiceFactory.simple().userService(), ServiceFactory.simple().courseService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final String COURSE_ID_REQUEST_PARAM_NAME = "id";
    public static final String STUDENTS_ATTRIBUTE_NAME = "students";
    public static final String STUDENTS_PAGE = "page.students";
    private static final String TITLE_ATTRIBUTE_NAME = "title";

    private final UserService userService;
    private final CourseService courseService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCheckStudentsCommandPage(UserService userService, CourseService courseService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.userService = userService;
        this.courseService = courseService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final long courseId = Long.parseLong(request.getParameter(COURSE_ID_REQUEST_PARAM_NAME));
        List<User> students = userService.findStudentsByCourseId(courseId);
        Course course = courseService.findById(courseId);
        request.addAttributeToJsp(STUDENTS_ATTRIBUTE_NAME, students);
        String title = "Check"+" "+course.getTitle() + " " + "course ♥";
        request.addAttributeToJsp(TITLE_ATTRIBUTE_NAME, title);
        return requestFactory.createForwardResponse(propertyContext.get(STUDENTS_PAGE));
    }
}
