package com.epam.jwd.onlinetraining.controller.command.logincommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;

public enum ShowSignupPageCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String LOGIN_PAGE = "page.login";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowSignupPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(LOGIN_PAGE));
    }
}
