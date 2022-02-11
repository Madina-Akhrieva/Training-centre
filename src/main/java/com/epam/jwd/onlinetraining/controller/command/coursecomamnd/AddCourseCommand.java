package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Mentor;
import com.epam.jwd.onlinetraining.service.api.CourseService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.exception.WrongDescriptionException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public enum AddCourseCommand implements Command {
    INSTANCE(ServiceFactory.simple().courseService(), RequestFactory.getInstance(), PropertyContext.instance());
    private static final Logger LOGGER = LogManager.getLogger(AddCourseCommand.class);

    private static final String IF_ADDED_ATTRIBUTE = "isAddedMessage";
    private static final String INVALID_COURSE_MESSAGE = "Course information is invalid";
    private static final String COURSE_JSP_PAGE = "page.manage_courses";
    private static final String ADD_COURSE_JSP_PAGE = "page.add_course";
    private static final String INDEX_JSP_PATH = "page.index";
    private static final String WRONG_DESCRIPTION_ATTRIBUTE = "wrongDescriptionAttribute";
    private static final String WRONG_DESCRIPTION_MESSAGE = "Description has wrong symbols";
    private static final String WRONG_TITLE_ATTRIBUTE = "wrongTitleAttribute";
    private static final String WRONG_TITLE_MESSAGE = "Title contains wrong symbols. Check once more please ♥";
    private static final String SUCCESSFUL_SIGNUP_ATTRIBUTE = "successfulSignupMessage";
    private static final String SUCCESSFUL_SIGNUP_MESSAGE_TEXT = "Course is added successfully ♥";
    private static final String COURSES_ATTRIBUTE_NAME = "courses";

    private final CourseService courseService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    AddCourseCommand(CourseService courseService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.courseService = courseService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            LOGGER.info("We are in execute method in AddCourseCommand");
            final String title = request.getParameter("title");
            final String learning_language = request.getParameter("learning_language");
            final String description = request.getParameter("description");
            courseService.create(new Course(title, learning_language, description));
            final List<Course> courses = courseService.findAll();
            request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, courses);
            request.addToSession(IF_ADDED_ATTRIBUTE, "course is added");

        } catch (WrongDescriptionException e) {
            LOGGER.warn("WrongDescriptionException is caught");
            request.addToSession(WRONG_DESCRIPTION_ATTRIBUTE, WRONG_DESCRIPTION_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(ADD_COURSE_JSP_PAGE));
        } catch (WrongTitleException e) {
            LOGGER.warn("WrongTitleException is caught");
            request.addToSession(WRONG_TITLE_ATTRIBUTE, WRONG_TITLE_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(ADD_COURSE_JSP_PAGE));
        }
        request.addToSession(SUCCESSFUL_SIGNUP_ATTRIBUTE, SUCCESSFUL_SIGNUP_MESSAGE_TEXT);
        return requestFactory.createForwardResponse(propertyContext.get(COURSE_JSP_PAGE));


    }
}
