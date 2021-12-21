package com.epam.jwd.onlinetraining.controller.command.taskscommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.service.api.CourseService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;

import java.util.Optional;

public enum AddTaskCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String ADD_COURSE = "page.add_task";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    AddTaskCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(ADD_COURSE));
    }

//    INSTANCE(ServiceFactory.simple().courseService(), RequestFactory.getInstance(), PropertyContext.instance());
//
//
//    private static final String IF_ADDED_ATTRIBUTE = "message";
//    private static final String INVALID_COURSE_MESSAGE = "Course information is invalid";
//    private static final String ADD_COURSE_JSP_PAGE = "page.add_course";
//
//    private final CourseService courseService;
//    private final RequestFactory requestFactory;
//    private final PropertyContext propertyContext;
//
//    AddCourseCommand(CourseService courseService, RequestFactory requestFactory, PropertyContext propertyContext) {
//        this.courseService = courseService;
//        this.requestFactory = requestFactory;
//        this.propertyContext = propertyContext;
//    }
//
//    @Override
//    public CommandResponse execute(CommandRequest request) {
//
//        final String title = request.getParameter("title");
//        final String learning_language = request.getParameter("learning_language");
//        final String description = request.getParameter("description");
//
//        final Optional<Course> course = courseService.create(new Course(title, learning_language, description));
//        if (course.isPresent()) {
//            request.addAttributeToJsp(IF_ADDED_ATTRIBUTE, INVALID_COURSE_MESSAGE);
//            return requestFactory.createForwardResponse(propertyContext.get(ADD_COURSE_JSP_PAGE));
//        }
//        request.addAttributeToJsp(IF_ADDED_ATTRIBUTE, "course is added");
//        return requestFactory.createRedirectResponse(propertyContext.get(ADD_COURSE_JSP_PAGE));
//
//    }
}
