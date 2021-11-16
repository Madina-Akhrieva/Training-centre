package com.epam.jwd.onlinetraining.controller.impl;

import com.epam.jwd.onlinetraining.controller.command.Command;

public enum CommandRegistry {
    MAIN_PAGE(ShowMainPageCommand.INSTANCE),
    DEFAULT(ShowMainPageCommand.INSTANCE);

    private final Command command;

    CommandRegistry(Command command) {
        this.command = command;
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
            if (constant.name().equals(name)) {
                return constant.getCommand();
            }

        }
         return DEFAULT.command;
    }
}
