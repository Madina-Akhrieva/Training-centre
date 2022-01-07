package com.epam.jwd.onlinetraining.controller.command.completetaskcommand;

import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;

public enum ShowCheckTasksPage implements Command {
    INSTANCE();

    private static final String COURSE_ID_REQUEST_PARAM_NAME = "cid";
    private static final String USER_ID_REQUEST_PARAM_NAME = "uid";

    @Override
    public CommandResponse execute(CommandRequest request) {
        final long courseId = Long.parseLong(request.getParameter(COURSE_ID_REQUEST_PARAM_NAME));
        final long userId = Long.parseLong(request.getParameter(USER_ID_REQUEST_PARAM_NAME));

        return null;
    }
}
