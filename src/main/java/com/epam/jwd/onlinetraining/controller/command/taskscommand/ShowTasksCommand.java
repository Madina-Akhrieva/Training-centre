package com.epam.jwd.onlinetraining.controller.command.taskscommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.controller.command.maincommand.ShowMainPageCommand;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.api.EntityService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public enum ShowTasksCommand implements Command {
    INSTANCE(ServiceFactory.simple().serviceFor(Task.class),
            RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(ShowMainPageCommand.class);


    private static final String TASKS_ATTRIBUTE_NAME = "tasks";
    private static final String WATCH_TASKS = "page.watch_tasks";
    private static final String ID_REQUEST_PARAM_NAME = "id";

    private final EntityService<Task> taskService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowTasksCommand(EntityService<Task> taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.taskService = taskService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        LOGGER.info("We're in ShowTaskCommand execute method");
        long id = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
        final List<Task> tasks = taskService.findAll(id);
        request.addAttributeToJsp(TASKS_ATTRIBUTE_NAME, tasks);
        return requestFactory.createForwardResponse(propertyContext.get(WATCH_TASKS));
    }
}
