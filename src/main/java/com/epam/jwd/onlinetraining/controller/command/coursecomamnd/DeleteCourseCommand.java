package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.service.api.CourseService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum DeleteCourseCommand implements Command {
    INSTANCE(ServiceFactory.simple().courseService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(EditCourseCommand.class);
    private static final String MANAGE_COURSES_PAGE = "page.manage_courses";
    private static final String COURSES_ATTRIBUTE_NAME = "courses";
    private static final String ID_REQUEST_PARAM_NAME = "id";


    private final CourseService courseService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    DeleteCourseCommand(CourseService courseService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.courseService = courseService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }


    @Override
    public CommandResponse execute(CommandRequest request) {
        LOGGER.trace("deleteCourseCommand ");
        long id = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
        boolean isDeleted = courseService.delete(id);
        final List<Course> courses = courseService.findAll();
        request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, courses);
        return requestFactory.createForwardResponse(propertyContext.get(MANAGE_COURSES_PAGE));
    }

}
