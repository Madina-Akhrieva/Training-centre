package com.epam.jwd.onlinetraining.controller.command;

public enum CommandRegistry {
    MAIN_PAGE(ShowMainPageCommand.INSTANCE,"main_page"),
    SIGN_UP_PAGE(SignUpPageCommand.INSTANCE, "sign_up"),
    LOGIN_PAGE(ShowLoginPageCommand.INSTANCE, "show_login"),
    DEFAULT(ShowMainPageCommand.INSTANCE, "");

    private final Command command;
    private final String path;

    CommandRegistry(Command command, String path) {
        this.command = command;
        this.path = path;
    }

    public Command getCommand() {
        return command;
    }

    static Command of(String name) {
        for (CommandRegistry constant : values()) {
            if (constant.path.equalsIgnoreCase(name)) {
                return constant.command;
            }
        }
        return DEFAULT.command;
    }
}
