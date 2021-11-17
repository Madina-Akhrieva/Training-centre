package com.epam.jwd.onlinetraining.controller.impl;

import com.epam.jwd.onlinetraining.controller.command.Command;
import com.epam.jwd.onlinetraining.controller.command.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.CommandResponse;

public class ShowLoginPageCommand implements Command {
    public static ShowLoginPageCommand INSTANCE  = new ShowLoginPageCommand();

    private static final CommandResponse FORWARD_TO_LOGIN_PAGE_RESPONSE = new CommandResponse() {
        @Override
        public Boolean isRedirect() {
            return false;
        }

        @Override
        public String getPath() {
            return "/WEB-INF/jsp/login_page.jsp";
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return FORWARD_TO_LOGIN_PAGE_RESPONSE;
    }
}
