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

public enum ShowEditCourseCommand implements Command {
    INSTANCE(ServiceFactory.simple().serviceFor(Course .class),
                RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(ShowMainPageCommand.class);


    private static final String COURSES_ATTRIBUTE_NAME = "courses";
    private static final String MAIN_PAGE = "page.edit_course";

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
        final List<Course> courses = service.findAll();
        request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, courses);
        return requestFactory.createForwardResponse(propertyContext.get(MAIN_PAGE));
    }
}
