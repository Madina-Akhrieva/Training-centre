package com.epam.jwd.onlinetraining.controller.command.common;

/**
 * com.epam.jwd.onlinetraining.controller.command.common public interface Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface Command {

    CommandResponse execute(CommandRequest request);

    static Command of(String name) {
        return CommandRegistry.of(name);
    }

}
