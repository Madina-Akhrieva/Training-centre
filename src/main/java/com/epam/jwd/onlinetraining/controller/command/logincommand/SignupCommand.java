package com.epam.jwd.onlinetraining.controller.command.logincommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.api.AccountService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.UserService;

import java.util.Optional;

public enum SignupCommand implements Command {
    INSTANCE(ServiceFactory.simple().accountService(), ServiceFactory.simple().userService(), RequestFactory.getInstance(), PropertyContext.instance());

    public static final String INDEX_JSP_PATH = "page.index";
    public static final String LOGIN_JSP_PATH = "page.login";

    private static final String ERROR_SIGNUP_PASS_ATTRIBUTE = "errorSignPassMessage";
    private static final String INVALID_SIGNUP_PASS_MESSAGE = "Such account already exist.";
    private static final String ACCOUNT_SESSION_ATTRIBUTE_NAME = "account";
    public static final String EMAIL_REQUEST_PARAM_NAME = "email";
    public static final String PASSWORD_REQUEST_PARAM_NAME = "password";
    private static final String LASTNAME_REQUEST_PARAM_NAME ="lastname";
    private static final String FIRSTNAME_REQUEST_PARAM_NAME ="firstname" ;
    private static final String PHONE_REQUEST_PARAM_NAME ="phone";
    private static final String USER_ATTRIBUTE_NAME = "user";


    private final AccountService accountService;
    private final UserService userService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;


    SignupCommand(AccountService accountService, UserService userService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.userService=userService;
        this.accountService = accountService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request){
        if (request.sessionExists() && request.retrieveFromSession(ACCOUNT_SESSION_ATTRIBUTE_NAME).isPresent()) {

        }
        final String login = request.getParameter(EMAIL_REQUEST_PARAM_NAME);
        final String password = request.getParameter(PASSWORD_REQUEST_PARAM_NAME);
        final String lastname = request.getParameter(LASTNAME_REQUEST_PARAM_NAME);
        final String firstname = request.getParameter(FIRSTNAME_REQUEST_PARAM_NAME);
        final String phone = request.getParameter(PHONE_REQUEST_PARAM_NAME);
//        final User user = new User(lastname, firstname, phone);
//        userService.create(user);

        final Optional<Account> account = accountService.register(login, password);
        final Optional<User> user = userService.register(account.get().getId(), phone, firstname, lastname);
        if (!account.isPresent()) {
            request.addAttributeToJsp(ERROR_SIGNUP_PASS_ATTRIBUTE, LOGIN_JSP_PATH);
            return requestFactory.createForwardResponse(propertyContext.get(LOGIN_JSP_PATH));
        }
        request.cleareSession();
        request.createSession();
        request.addToSession(ACCOUNT_SESSION_ATTRIBUTE_NAME, account.get());
        request.addAttributeToJsp(USER_ATTRIBUTE_NAME, user);
        return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));

    }
}
