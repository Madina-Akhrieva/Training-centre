package com.epam.jwd.onlinetraining.controller.command.logincommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.exception.NoUserFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum LogoutCommand implements Command {
    INSTANCE(RequestFactory.getInstance());
    private static final Logger LOGGER = LogManager.getLogger(LogoutCommand.class);
    public static final String ACCOUNT_SESSION_ATTRIBUTE_NAME = "account";
    public static final String INDEX_PATH = "/";
    private final RequestFactory requestFactory;

    LogoutCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (noLoggedInUserPresent(request)) {
            try {
                throw new NoUserFoundException("No user is found. Can't log out");
            } catch (NoUserFoundException e) {
                LOGGER.warn("No user is found. Can't logout");
            }
            return null;
        }
        request.cleareSession();
        return requestFactory.createRedirectResponse(INDEX_PATH);

    }

    private boolean noLoggedInUserPresent(CommandRequest request) {
        return !request.sessionExists()
                || (request.sessionExists()
                && !request.retrieveFromSession(ACCOUNT_SESSION_ATTRIBUTE_NAME)
                .isPresent()
        );
    }
}
