package com.epam.jwd.onlinetraining.controller.command.logincommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.service.api.AccountService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.controller.command.logincommand public enum LoginCommand
 * extends Enum<LoginCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum LoginCommand implements Command {
    INSTANCE(ServiceFactory.simple().accountService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private static final String INDEX_JSP_PATH = "page.index";
    private static final String LOGIN_JSP_PATH = "page.login";
    private static final String LOGIN_REQUEST_PARAM_NAME = "email";
    private static final String PASSWORD_REQUEST_PARAM_NAME = "password";
    private static final String ERROR_LOGIN_PASS_ATTRIBUTE = "errorLoginPassMessage";
    private static final String INVALID_LOGIN_PASS_MESSAGE = "Account doesn't exist. Sign in first please ♥";
    private static final String ACCOUNT_SESSION_ATTRIBUTE_NAME = "account";
    private static final String EMPTY_INPUTS_ATTRIBUTE = "emptyInputsMessage";
    private static final Object EMPTY_INPUTS_MESSAGE_TEXT = "None input is allowed to be empty!";

    private final AccountService accountService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    LoginCommand(AccountService accountService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.accountService = accountService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final String login = request.getParameter(LOGIN_REQUEST_PARAM_NAME);
            final String password = request.getParameter(PASSWORD_REQUEST_PARAM_NAME);
            final Optional<Account> account;
            account = accountService.authenticate(login, password);
            if (!account.isPresent()) {
                request.addToSession(ERROR_LOGIN_PASS_ATTRIBUTE, INVALID_LOGIN_PASS_MESSAGE);
                return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
            }
            request.cleareSession();
            request.createSession();
            request.addToSession(ACCOUNT_SESSION_ATTRIBUTE_NAME, account.get());
        } catch (EmptyInputException e) {
            LOGGER.warn("Empty inputs exception");
            request.addAttributeToJsp(EMPTY_INPUTS_ATTRIBUTE, EMPTY_INPUTS_MESSAGE_TEXT);
            request.addToSession(EMPTY_INPUTS_ATTRIBUTE, EMPTY_INPUTS_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }
        return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));

    }
}
