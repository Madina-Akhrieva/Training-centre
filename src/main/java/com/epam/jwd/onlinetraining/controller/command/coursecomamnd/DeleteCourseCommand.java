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

public enum DeleteCourseCommand implements Command {
    INSTANCE(ServiceFactory.simple().courseService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(EditCourseCommand.class);
    private static final String COURSE_ATTRIBUTE_NAME = "course";
    private static final String EDIT_COURSE_PAGE = "page.edit_course";

    private final CourseService courseService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    DeleteCourseCommand(CourseService courseService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.courseService = courseService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    private static final String ID_REQUEST_PARAM_NAME = "id";

    @Override
    public CommandResponse execute(CommandRequest request) {

        long id = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));

        Course course = courseService.findById(id);


        LOGGER.info("Id which came is: {}", id);

        request.addAttributeToJsp(COURSE_ATTRIBUTE_NAME, course);
        return requestFactory.createForwardResponse(propertyContext.get(EDIT_COURSE_PAGE));
    }

}
