package com.epam.jwd.onlinetraining.controller.command;

public enum SignUpPageCommand implements Command {
    INSTANCE;
    public static final CommandResponse FORWARD_TO_SIGN_IN_PAGE_RESPONSE = new CommandResponse() {
        @Override
        public boolean isRedirect() {
            return false;
        }

        @Override
        public String getPath() {
            return "WEB-INF/jsp/login_signup.jsp";
        }
    };
    @Override
    public CommandResponse execute(CommandRequest request) {
        return FORWARD_TO_SIGN_IN_PAGE_RESPONSE;
    }
}
