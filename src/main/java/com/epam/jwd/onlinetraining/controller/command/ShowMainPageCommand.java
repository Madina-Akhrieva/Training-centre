package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.service.api.EntityService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
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

//        List<String> courses = new ArrayList<>();
//        for (Course course: list) {
//            courses.add(course.getTitle());
//        }
//        final List<String> courses = Arrays.asList()

//        List<String> courses = Arrays.asList("as", "as");
//        List<Course> courses = Arrays.asList(
//                new Course("title", 12, "language", "description"),
//                new Course("title", 12, "language", "description"),
//                new Course("title", 12, "language", "description"),
//                new Course("title", 12, "language", "description"),
//                new Course("title", 12, "language", "description"),
//                new Course("title", 12, "language", "description"),
//                new Course("title", 12, "language", "description"),
//                new Course("title", 12, "language", "description")
//
//
//                );
//        List<String> courses = new ArrayList<>();

        request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, courses);
        return FORWARD_TO_MAIN_PAGE_RESPONSE;
    }
}
