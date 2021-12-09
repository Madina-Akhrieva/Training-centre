package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;

public enum ShowLoginPageCommand implements Command {

    INSTANCE(RequestFactory.getInstance());

    private static final String LOGIN_JSP_PATH = "/WEB-INF/jsp/login_signup.jsp";

    private final RequestFactory requestFactory;

    ShowLoginPageCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(LOGIN_JSP_PATH);
    }
}

//
//public enum ShowLoginPageCommand implements Command {
//    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());
//
//    private static final String LOGIN_PAGE = "page.login";
//
//    private final RequestFactory requestFactory;
//    private final PropertyContext propertyContext;
//
//    ShowLoginPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
//        this.requestFactory = requestFactory;
//        this.propertyContext = propertyContext;
//    }
//
//    @Override
//    public CommandResponse execute(CommandRequest request) {
//        return requestFactory.createForwardResponse(propertyContext.get(LOGIN_PAGE));
//    }
//}
