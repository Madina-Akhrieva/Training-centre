package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;


public enum ShowProfilePageCommand implements Command {

    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String PROFILE_PAGE = "page.profile";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowProfilePageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(PROFILE_PAGE));
    }
}

