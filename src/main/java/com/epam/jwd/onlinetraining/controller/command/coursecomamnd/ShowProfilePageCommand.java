package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.api.AccountService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.UserService;

import java.util.List;
import java.util.Optional;


public enum ShowProfilePageCommand implements Command {

    INSTANCE(ServiceFactory.simple().userService(), ServiceFactory.simple().accountService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final String PROFILE_PAGE = "page.profile";
    private static final String ID_REQUEST_PARAM_NAME = "id" ;
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String ACCOUNT_ATTRIBUTE_NAME = "account";
    private static final String COURSES_ATTRIBUTE_NAME = "courses";


    private final UserService userService;
    private final AccountService accountService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowProfilePageCommand(UserService userService, AccountService accountService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.userService = userService;
        this.accountService = accountService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        long id = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
        final User user = userService.findById(id);
        final Optional<Account> account = accountService.findById(id);
        List<Course> allCoursesByUserId = userService.findAllCoursesByUserId(id);
        request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, allCoursesByUserId);
        request.addAttributeToJsp(USER_ATTRIBUTE_NAME, user);
        request.addAttributeToJsp(ACCOUNT_ATTRIBUTE_NAME, account.get());
        return requestFactory.createForwardResponse(propertyContext.get(PROFILE_PAGE));
    }
}

