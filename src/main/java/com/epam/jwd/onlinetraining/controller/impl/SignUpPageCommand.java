package com.epam.jwd.onlinetraining.controller.impl;

import com.epam.jwd.onlinetraining.controller.command.Command;
import com.epam.jwd.onlinetraining.controller.command.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.CommandResponse;

public class SignUpPageCommand implements Command {
    public static SignUpPageCommand INSTANCE = new SignUpPageCommand();
    public static final CommandResponse FORWARD_TO_SIGN_IN_PAGE_RESPONSE = new CommandResponse() {
        @Override
        public Boolean isRedirect() {
            return false;
        }

        @Override
        public String getPath() {
            return "WEB-INF/jsp/sign_up.jsp";
        }
    };
    @Override
    public CommandResponse execute(CommandRequest request) {
        return FORWARD_TO_SIGN_IN_PAGE_RESPONSE;
    }
}
