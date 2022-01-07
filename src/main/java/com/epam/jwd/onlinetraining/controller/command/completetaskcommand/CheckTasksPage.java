package com.epam.jwd.onlinetraining.controller.command.completetaskcommand;

import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;

public enum CheckTasksPage implements Command {
    INSTANCE();

    @Override
    public CommandResponse execute(CommandRequest request) {
        return null;
    }
}
