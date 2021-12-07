package com.epam.jwd.onlinetraining.controller.command;

public enum ShowProfilePageCommand implements Command {

    INSTANCE;

    public static final CommandResponse FORWARD_TO_PROFILE_PAGE_RESPONSE = new CommandResponse() {

        @Override
        public boolean isRedirect() {
            return false;
        }

        @Override
        public String getPath() {
            return "/WEB-INF/jsp/profile.jsp";
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return FORWARD_TO_PROFILE_PAGE_RESPONSE;
    }
}

