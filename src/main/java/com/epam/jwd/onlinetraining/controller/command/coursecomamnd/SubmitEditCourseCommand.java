package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.service.api.CourseService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum SubmitEditCourseCommand implements Command {
    INSTANCE(ServiceFactory.simple().courseService(), RequestFactory.getInstance(), PropertyContext.instance());


    public static final String MANAGE_COURSES_JSP_PAGE = "page.manage_courses";

    private static final String IF_ADDED_ATTRIBUTE = "message";
    private static final String INVALID_COURSE_MESSAGE = "smth went wrong";
    private static final String COURSES_ATTRIBUTE_NAME = "courses";
    private static final String EDIT_COURSE_JSP_PAGE = "page.edit_course";
    private static final String ID_REQUEST_PARAM_NAME = "id";

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
        long id = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
        Course oldCourse = courseService.findById(id);
        final String title = request.getParameter("title");
        final String learning_language = request.getParameter( "learning_language");
        final String description = request.getParameter( "description");
        Course course = new Course( title,  learning_language, description );
        final boolean isUpdated = courseService.update(course, oldCourse.getTitle());
        if (!isUpdated) {
            //todo : если курса нет, значит, обновление не удалось и нужно добавить сообщение о том, что
            //todo:курс не был обновлен на страниицу edit_course
            request.addAttributeToJsp(IF_ADDED_ATTRIBUTE, INVALID_COURSE_MESSAGE);
            return requestFactory.createForwardResponse(propertyContext.get(EDIT_COURSE_JSP_PAGE));
        }
        final List<Course> courses = courseService.findAll();
        request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, courses);
        return requestFactory.createForwardResponse(propertyContext.get(MANAGE_COURSES_JSP_PAGE));

    }

}
