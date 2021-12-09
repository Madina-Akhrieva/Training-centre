package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.service.api.EntityService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum ShowMainPageCommand implements Command {

    INSTANCE(ServiceFactory.simple().serviceFor(Course.class));

    private static final Logger LOGGER = LogManager.getLogger(ShowMainPageCommand.class);

    private static final CommandResponse FORWARD_TO_MAIN_PAGE_RESPONSE = new CommandResponse() {
        @Override
        public boolean isRedirect() {
            return false;
        }

        @Override
        public String getPath() {
            return "/WEB-INF/jsp/main.jsp";
        }
    };
    public static final String COURSES_ATTRIBUTE_NAME = "courses";

    private final EntityService<Course> service;

    ShowMainPageCommand(EntityService<Course> service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<Course> courses = service.findAll();
        request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, courses);
        return FORWARD_TO_MAIN_PAGE_RESPONSE;
    }
}

//
//public enum ShowMainPageCommand implements Command {
//    INSTANCE(ServiceFactory.simple().serviceFor(Course.class),
//            RequestFactory.getInstance(), PropertyContext.instance());
//
//    private static final String COURSES_ATTRIBUTE_NAME = "courses";
//    private static final String MAIN_PAGE = "page.main";
//
//    private final EntityService<Course> service;
//    private final RequestFactory requestFactory;
//    private final PropertyContext propertyContext;
//
//    ShowMainPageCommand(EntityService<Course> service, RequestFactory requestFactory, PropertyContext propertyContext) {
//        this.service = service;
//        this.requestFactory = requestFactory;
//        this.propertyContext = propertyContext;
//    }
//
//    @Override
//    public CommandResponse execute(CommandRequest request) {
//        final List<Course> bikes = service.findAll();
//        request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, bikes);
//        return requestFactory.createForwardResponse(propertyContext.get(MAIN_PAGE));
//    }
//
//}
