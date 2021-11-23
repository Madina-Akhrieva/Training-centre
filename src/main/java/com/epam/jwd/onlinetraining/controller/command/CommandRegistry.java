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

    //он итерируется по всех константам enum и если виит что ее название соответсвует  названию команды которую
    // попросили то возращает подходящую команду, в противном случае возвращает default команду, которая будет указывать
    // на страницу 404 или на index jsp страницу

    //итерируется по константам enum, проверяет, чтобы они соответствовали name, если видит,
    // что название конст соотв комманды то возвращ command из const
    public static Command of(String name) {
        for (CommandRegistry constant : values()) {
            if (constant.path.equalsIgnoreCase(name)) {
                return constant.getCommand();
            }

        }
        return DEFAULT.command;
    }
}
