package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.service.api.CourseService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.exception.WrongDescriptionException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;
import com.epam.jwd.onlinetraining.service.validator.CourseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum SubmitEditCourseCommand implements Command {
    INSTANCE(ServiceFactory.simple().courseService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(SubmitEditCourseCommand.class);
    public static final String MANAGE_COURSES_JSP_PAGE = "page.manage_courses";

    private static final String COURSES_ATTRIBUTE_NAME = "courses";
    private static final String EDIT_COURSE_JSP_PAGE = "page.edit_course";
    private static final String ID_REQUEST_PARAM_NAME = "id";
    private static final String WRONG_DESCRIPTION_ATTRIBUTE = "wrongDescriptionAttribute";
    private static final String WRONG_DESCRIPTION_MESSAGE = "Description has wrong symbols";
    private static final String WRONG_TITLE_ATTRIBUTE = "wrongTitleAttribute";
    private static final String WRONG_TITLE_MESSAGE = "Title contains wrong symbols. Check once more please ♥";
    private static final String SUCCESSFUL_EDIT_ATTRIBUTE = "successfulEditMessage";
    private static final String SUCCESSFUL_EDIT_MESSAGE_TEXT = "Course is edited successfully ♥";
    private static final String COURSE_ID_REQUEST_ATTRIBUTE_NAME = "courseId";

    private final CourseService courseService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    SubmitEditCourseCommand(CourseService courseService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.courseService = courseService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }


    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            long id = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
            final String title = request.getParameter("title");
            final String learning_language = request.getParameter("learning_language");
            final String description = request.getParameter("description");
            Course oldCourse = courseService.findById(id);
            Course course = new Course(title, learning_language, description);
            request.addAttributeToJsp(COURSE_ID_REQUEST_ATTRIBUTE_NAME, id);

            courseService.update(course, oldCourse.getTitle());
            final List<Course> courses = courseService.findAll();
            request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, courses);
        } catch (WrongDescriptionException e) {
            LOGGER.warn("WrongDescriptionException is caught");
            request.addToSession(WRONG_DESCRIPTION_ATTRIBUTE, WRONG_DESCRIPTION_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(EDIT_COURSE_JSP_PAGE));
        } catch (WrongTitleException e) {
            LOGGER.warn("WrongTitleException is caught");
            request.addToSession(WRONG_TITLE_ATTRIBUTE, WRONG_TITLE_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(EDIT_COURSE_JSP_PAGE));

        }
        request.addToSession(SUCCESSFUL_EDIT_ATTRIBUTE, SUCCESSFUL_EDIT_MESSAGE_TEXT);
        return requestFactory.createForwardResponse(propertyContext.get(MANAGE_COURSES_JSP_PAGE));

    }

}
