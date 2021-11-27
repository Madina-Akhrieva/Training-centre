package com.epam.jwd.onlinetraining.controller.command;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;

public class ShowLoginPageCommand implements Command {

    public static final ShowLoginPageCommand INSTANCE = new ShowLoginPageCommand(RequestFactory.getInstance());

    private static final String LOGIN_JSP_PATH = "/WEB-INF/jsp/login.jsp";

    private final RequestFactory requestFactory;

    ShowLoginPageCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(LOGIN_JSP_PATH);
    }
}
