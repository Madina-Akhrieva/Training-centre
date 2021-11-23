package com.epam.jwd.onlinetraining.controller.command;

public class SignUpPageCommand implements Command {
    public static SignUpPageCommand INSTANCE = new SignUpPageCommand();
    public static final CommandResponse FORWARD_TO_SIGN_IN_PAGE_RESPONSE = new CommandResponse() {
        @Override
        public boolean isRedirect() {
            return false;
        }

        @Override
        public String getPath() {
            return "WEB-INF/jsp/signup.jsp";
        }
    };
    @Override
    public CommandResponse execute(CommandRequest request) {
        return FORWARD_TO_SIGN_IN_PAGE_RESPONSE;
    }
}
