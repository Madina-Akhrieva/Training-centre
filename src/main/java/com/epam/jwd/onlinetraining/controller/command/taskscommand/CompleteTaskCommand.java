package com.epam.jwd.onlinetraining.controller.command.taskscommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.api.EntityService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;

import java.util.List;

public enum CompleteTaskCommand implements Command {
    INSTANCE(ServiceFactory.simple().serviceFor(Task.class),
            RequestFactory.getInstance(), PropertyContext.instance());

    private static final String COMPLETE_TASK = "page.complete_task";
    private static final String ID_REQUEST_PARAM_NAME = "id";
    private static final String COURSE_ID_PARAM = "id";
    private static final String TASKS_ATTRIBUTE_NAME ="tasks";

    private final EntityService<Task> service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    CompleteTaskCommand(EntityService<Task> service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = service;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        long id = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
        final List<Task> tasks = service.findAll(id);
        request.addAttributeToJsp(COURSE_ID_PARAM, id);
        request.addAttributeToJsp(TASKS_ATTRIBUTE_NAME, tasks);

        return requestFactory.createForwardResponse(propertyContext.get(COMPLETE_TASK));
    }
}



