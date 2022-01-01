package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum AddCourseToUserCommand implements Command {
    INSTANCE(ServiceFactory.simple().userService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(AddCourseToUserCommand.class);
    private static final String IS_ADDED_ATTRIBUTE_NAME = "isAddedMessage";
    private static final String PROFILE_JSP_PAGE = "page.profile";
    private static final String COURSE_ID_REQUEST_PARAM_NAME = "course_id";
    private static final String USER_ID_REQUEST_PARAM_NAME = "user_id";
    private static final String IS_ADDED_MESSAGE = "Course is successfully added ♥";
    private static final Object IS_NOT_ADDED_MESSAGE = "You  have this course in your profile ♥";
    private static final String COURSES_ATTRIBUTE_NAME = "courses";
    private static final String USER_ATTRIBUTE = "user";

    private final UserService userService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    AddCourseToUserCommand(UserService userService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.userService = userService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        long courseId = Long.parseLong(request.getParameter(COURSE_ID_REQUEST_PARAM_NAME));
        long userId = Long.parseLong(request.getParameter(USER_ID_REQUEST_PARAM_NAME));
        User user = userService.findById(userId);
        boolean subscriptionStringExist = userService.checkIfSubscriptionStringExist(courseId, userId);
        if (!subscriptionStringExist){
            userService.addCourseToUser(courseId, userId);
            request.addAttributeToJsp(IS_ADDED_ATTRIBUTE_NAME, IS_ADDED_MESSAGE);
        }else{
            request.addAttributeToJsp(IS_ADDED_ATTRIBUTE_NAME, IS_NOT_ADDED_MESSAGE);
        }
        List<Course> allCoursesByUserId = userService.findAllCoursesByUserId(userId);
        request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, allCoursesByUserId);
        request.addAttributeToJsp(USER_ATTRIBUTE, user);
        return requestFactory.createForwardResponse(propertyContext.get(PROFILE_JSP_PAGE));
    }
}
