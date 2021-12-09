package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.service.api.AccountService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;

import java.util.Optional;

public enum LoginCommand implements Command {
    INSTANCE(ServiceFactory.simple().accountService(), RequestFactory.getInstance());

    public static final String INDEX_JSP_PATH = "/";
    public static final String LOGIN_JSP_PATH= "/WEB-INF/jsp/login_signup.jsp";

    private static final String ERROR_LOGIN_PASS_ATTRIBUTE = "errorLoginPassMessage";
    private static final String INVALID_LOGIN_PASS_MESSAGE = "Invalid login or password";
    private static final String ACCOUNT_SESSION_ATTRIBUTE_NAME = "account";
    public static final String LOGIN_REQUEST_PARAM_NAME = "email";
    public static final String PASSWORD_REQUEST_PARAM_NAME = "password";

    private final AccountService accountService;
    private final RequestFactory requestFactory;


    LoginCommand(AccountService accountService, RequestFactory requestFactory) {
        this.accountService = accountService;
        this.requestFactory = requestFactory;
    }

    //stopped добавляя RequestFactory в конструтор


    @Override
    public CommandResponse execute(CommandRequest request) {
        if (request.sessionExists() && request.retrieveFromSession(ACCOUNT_SESSION_ATTRIBUTE_NAME).isPresent()) {
            //error : user already logged in
            return null;
        }
        final String login = request.getParameter(LOGIN_REQUEST_PARAM_NAME);
        final String password = request.getParameter( PASSWORD_REQUEST_PARAM_NAME);
        final Optional<Account> account = accountService.authenticate(login, password);
        if (!account.isPresent()) {
            request.addAttributeToJsp(ERROR_LOGIN_PASS_ATTRIBUTE, INVALID_LOGIN_PASS_MESSAGE);
            requestFactory.createForwardResponse(LOGIN_JSP_PATH);
        }
        request.clareSession();
        request.createSession();
        request.addToSession(ACCOUNT_SESSION_ATTRIBUTE_NAME, account.get());
        return requestFactory.createRedirectResponse(INDEX_JSP_PATH);

    }
}
