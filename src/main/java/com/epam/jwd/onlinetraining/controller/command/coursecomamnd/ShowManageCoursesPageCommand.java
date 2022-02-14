package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.controller.command.maincommand.ShowMainPageCommand;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.service.api.EntityService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * com.epam.jwd.onlinetraining.controller.command.coursecomamnd public enum ShowManageCoursesPageCommand
 * extends Enum<ShowManageCoursesPageCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum ShowManageCoursesPageCommand implements Command {
    INSTANCE(ServiceFactory.simple().serviceFor(Course.class),
            RequestFactory.getInstance(), PropertyContext.instance());

    private static final String COURSES_ATTRIBUTE_NAME = "courses";
    private static final String MANAGE_COURSES = "page.manage_courses";
    private static final String INDEX_JSP_PATH = "page.index";


    private final EntityService<Course> service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowManageCoursesPageCommand(EntityService<Course> service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = service;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final List<Course> courses = service.findAll();
            request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, courses);
            return requestFactory.createForwardResponse(propertyContext.get(MANAGE_COURSES));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }

    }
}
