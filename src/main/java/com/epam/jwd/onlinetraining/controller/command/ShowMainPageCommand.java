package com.epam.jwd.onlinetraining.controller.command;

public class ShowMainPageCommand implements Command {
    public static ShowMainPageCommand INSTANCE  = new ShowMainPageCommand();

    private static final CommandResponse FORWARD_TO_MAIN_PAGE_RESPONSE = new CommandResponse() {
        @Override
        public boolean isRedirect() {
            return false;
        }

        @Override
        public String getPath() {
            return "/WEB-INF/jsp/main.jsp";
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return FORWARD_TO_MAIN_PAGE_RESPONSE;
    }


}
