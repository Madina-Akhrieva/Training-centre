package com.epam.jwd.onlinetraining.controller.command;

public interface Command {

    CommandResponse execute(CommandRequest request);

    static Command of(String name) {
        Command command = CommandRegistry.of(name);
        return command;
    }
}
