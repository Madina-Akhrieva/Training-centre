package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.service.api.AccountService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;

import java.util.Optional;

public enum AddCourseCommand implements Command {
    INSTANCE(ServiceFactory.simple().accountService(), RequestFactory.getInstance(), PropertyContext.instance());

    public static final String INDEX_JSP_PATH = "page.index";
    public static final String LOGIN_JSP_PATH= "page.login";
    public static final String LOGIN_REQUEST_PARAM_NAME = "email";
    public static final String PASSWORD_REQUEST_PARAM_NAME = "password";

    private static final String ERROR_LOGIN_PASS_ATTRIBUTE = "errorLoginPassMessage";
    private static final String INVALID_LOGIN_PASS_MESSAGE = "Such account doesn't exist.";
    private static final String ACCOUNT_SESSION_ATTRIBUTE_NAME = "account";

    private final AccountService accountService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    AddCourseCommand(AccountService accountService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.accountService = accountService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (request.sessionExists() && request.retrieveFromSession(ACCOUNT_SESSION_ATTRIBUTE_NAME).isPresent()) {
            // todo error : user already logged in
            return null;
        }
        final String login = request.getParameter(LOGIN_REQUEST_PARAM_NAME);
        final String password = request.getParameter( PASSWORD_REQUEST_PARAM_NAME);
        final Optional<Account> account = accountService.authenticate(login, password);
        if (!account.isPresent()) {
            request.addAttributeToJsp(ERROR_LOGIN_PASS_ATTRIBUTE, INVALID_LOGIN_PASS_MESSAGE);
            return requestFactory.createForwardResponse(propertyContext.get(LOGIN_JSP_PATH));
        }
        request.cleareSession();
        request.createSession();
        request.addToSession(ACCOUNT_SESSION_ATTRIBUTE_NAME, account.get());
        return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));

    }
}
