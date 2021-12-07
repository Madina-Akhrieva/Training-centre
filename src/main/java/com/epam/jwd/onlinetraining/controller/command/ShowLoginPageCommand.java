package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;

public enum ShowLoginPageCommand implements Command {

    INSTANCE(PropertyContext.instance(), RequestFactory.getInstance());

    private  final PropertyContext propertyContext;

    private static final String LOGIN_PAGE = "login";

    private final RequestFactory requestFactory;

    ShowLoginPageCommand(PropertyContext propertyContext, RequestFactory requestFactory) {
        this.propertyContext = propertyContext;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(LOGIN_PAGE));
    }
}
