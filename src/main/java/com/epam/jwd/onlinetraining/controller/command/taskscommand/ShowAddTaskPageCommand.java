package com.epam.jwd.onlinetraining.controller.command.taskscommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;

public enum ShowAddTaskPageCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String ADD_TASK = "page.add_task";
    private static final String ID_REQUEST_PARAM_NAME = "id";
    private static final String COURSE_ID_PARAM = "courseId";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAddTaskPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        long id = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
        request.addAttributeToJsp(COURSE_ID_PARAM, id);
        return requestFactory.createForwardResponse(propertyContext.get(ADD_TASK));
    }
}
