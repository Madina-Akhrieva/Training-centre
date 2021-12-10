package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;

public enum LogoutCommand  implements Command{
    INSTANCE(RequestFactory.getInstance());
    public static final String ACCOUNT_SESSION_ATTRIBUTE_NAME = "account";
    public static final String INDEX_PATH = "/";
    private final RequestFactory requestFactory;

    LogoutCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (noLoggedInUserPresent(request)) {
            //error : no user fount, can't logout
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
