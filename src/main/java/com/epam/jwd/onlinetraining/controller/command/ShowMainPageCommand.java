package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.service.api.EntityService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;

import java.util.List;

public class ShowMainPageCommand implements Command {
    public static final ShowMainPageCommand INSTANCE  = new ShowMainPageCommand(ServiceFactory.simple().serviceFor(Course.class));

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

    public ShowMainPageCommand(EntityService<Course> service) {
        this.service = service;
    }


    @Override
    public CommandResponse execute(CommandRequest request) {
        List<Course> courses = service.findAll();
        request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, courses);
        return FORWARD_TO_MAIN_PAGE_RESPONSE;
    }


}
