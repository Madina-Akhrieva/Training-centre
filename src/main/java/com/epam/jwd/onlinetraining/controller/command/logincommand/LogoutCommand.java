package com.epam.jwd.onlinetraining.controller.command.logincommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.controller.exception.NoUserFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * com.epam.jwd.onlinetraining.controller.command.logincommand public enum LogoutCommand
 * extends Enum<LogoutCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum LogoutCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    public static final String NO_USER_IS_FOUND_EXCEPTION_MESSAGE = "No user is found. Can't log out";
    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);
    private static final String ACCOUNT_SESSION_ATTRIBUTE_NAME = "account";
    private static final String INDEX_PATH = "/";
    private static final String INDEX_JSP_PATH = "page.index";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;


    LogoutCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try{
            if (noLoggedInUserPresent(request)) {
                try {
                    throw new NoUserFoundException(NO_USER_IS_FOUND_EXCEPTION_MESSAGE);
                } catch (NoUserFoundException e) {
                    LOGGER.warn("No user is found. Can't logout");
                }
                return null;
            }
            request.cleareSession();
            return requestFactory.createRedirectResponse(INDEX_PATH);
        }catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }
    }

    private boolean noLoggedInUserPresent(CommandRequest request) {
        return !request.sessionExists()
                || (request.sessionExists()
                && !request.retrieveFromSession(ACCOUNT_SESSION_ATTRIBUTE_NAME)
                .isPresent()
        );
    }
}
