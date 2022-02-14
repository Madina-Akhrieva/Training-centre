package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.service.api.EntityService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;

import java.util.List;

/**
 * com.epam.jwd.onlinetraining.controller.command.coursecomamnd public enum ShowEditCourseCommand
 * extends Enum<ShowEditCourseCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum ShowEditCourseCommand implements Command {
    INSTANCE(ServiceFactory.simple().serviceFor(Course.class),
            RequestFactory.getInstance(), PropertyContext.instance());

    private static final String COURSES_ATTRIBUTE_NAME = "courses";
    private static final String MAIN_PAGE = "page.edit_course";
    private static final String INDEX_JSP_PATH = "page.index";


    private final EntityService<Course> service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowEditCourseCommand(EntityService<Course> service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = service;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final List<Course> courses = service.findAll();
            request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, courses);
            return requestFactory.createForwardResponse(propertyContext.get(MAIN_PAGE));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }


    }
}
