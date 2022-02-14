package com.epam.jwd.onlinetraining.controller.command.completetaskcommand;


import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * com.epam.jwd.onlinetraining.controller.command.completetaskcommand public enum DeleteTaskCommand
 * extends Enum<DeleteTaskCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum DeleteTaskCommand implements Command {
    INSTANCE(ServiceFactory.simple().taskService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(DeleteTaskCommand.class);
    private static final String MANAGE_COURSES_PAGE = "page.manage_courses";
    private static final String COURSES_ATTRIBUTE_NAME = "courses";
    private static final String ID_REQUEST_PARAM_NAME = "id";
    private static final String DELETED_MESSAGE_ATTRIBUTE = "deletedMessageAttribute";
    private static final Object IS_DELETED_MESSAGE = "Task deleted successfully!";
    private static final Object IS_NOT_DELETED_MESSAGE = "Task is not deleted successfully!";
    private static final String SUCCESSFUL_DELETE_ATTRIBUTE = "successfulDeleteMessage";
    private static final String INDEX_JSP_PATH = "page.index";

    private static final String SUCCESSFUL_DELETE_MESSAGE_TEXT = "Task is deleted successfully ♥";

    private final TaskService taskService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    DeleteTaskCommand(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.taskService = taskService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }


    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            LOGGER.trace("deleteTaskCommand ");
            long id = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
            boolean isDeleted = taskService.delete(id);
            if (isDeleted) {
                request.addAttributeToJsp(DELETED_MESSAGE_ATTRIBUTE, IS_DELETED_MESSAGE);
            } else {
                request.addAttributeToJsp(DELETED_MESSAGE_ATTRIBUTE, IS_NOT_DELETED_MESSAGE);
            }
            final List<Task> tasks = taskService.findAll();
            request.addAttributeToJsp(COURSES_ATTRIBUTE_NAME, tasks);
            request.addToSession(SUCCESSFUL_DELETE_ATTRIBUTE, SUCCESSFUL_DELETE_MESSAGE_TEXT);
            return requestFactory.createForwardResponse(propertyContext.get(MANAGE_COURSES_PAGE));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }

    }

}
