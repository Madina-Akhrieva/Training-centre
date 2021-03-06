package com.epam.jwd.onlinetraining.controller.command.taskscommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.controller.command.maincommand.ShowMainPageCommand;
import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.api.EntityService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * com.epam.jwd.onlinetraining.controller.command.taskscommand public enum ShowTasksCommand
 * extends Enum<ShowTasksCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum ShowTasksCommand implements Command {
    INSTANCE(ServiceFactory.simple().serviceFor(Task.class),
            RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(ShowMainPageCommand.class);
    private static final String TASKS_ATTRIBUTE_NAME = "tasks";
    private static final String WATCH_TASKS = "page.watch_tasks";
    private static final String INDEX_JSP_PATH = "page.index";
    private static final String COURSE_ID_REQUEST_PARAM_NAME = "id";
    private static final String COURSE_ID_REQUEST_ATTRIBUTE_NAME = "courseId";

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
        try {
            LOGGER.info("We're in ShowTaskCommand execute method");
            long courseId = Long.parseLong(request.getParameter(COURSE_ID_REQUEST_PARAM_NAME));
            final List<Task> tasks = taskService.findAll(courseId);
            request.addAttributeToJsp(TASKS_ATTRIBUTE_NAME, tasks);
            request.addAttributeToJsp(COURSE_ID_REQUEST_ATTRIBUTE_NAME, courseId);
            return requestFactory.createForwardResponse(propertyContext.get(WATCH_TASKS));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }

    }
}
