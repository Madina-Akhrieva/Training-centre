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

public enum GiveFeedbackCommand implements Command {
    INSTANCE(ServiceFactory.simple().taskService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(SendAnswerCommand.class);
    private static final String COURSE_ID_REQUEST_PARAM_NAME = "course_id";
    private static final String USER_ID_REQUEST_PARAM_NAME = "user_id";
    private static final String TASK_ID_REQUEST_PARAM_NAME = "task_id";
    private static final String FEEDBACK_REQUEST_PARAM_NAME = "feedback";
    private static final String GIVE_FEEDBACK_PAGE = "page.give_feedback";
    private static final String COURSE_ID_PARAM = "id";
    private static final String TASK_ATTRIBUTE_NAME = "task";

    private final TaskService taskService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    GiveFeedbackCommand(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.taskService = taskService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final long courseId = Long.parseLong(request.getParameter(COURSE_ID_REQUEST_PARAM_NAME));
        final long userId = Long.parseLong(request.getParameter(USER_ID_REQUEST_PARAM_NAME));
        final long taskId = Long.parseLong(request.getParameter(TASK_ID_REQUEST_PARAM_NAME));
        final Task task = taskService.findTasksByCourseIdTaskIdAndUserId(userId, courseId, taskId);
        request.addAttributeToJsp(TASK_ATTRIBUTE_NAME, task);
        request.addAttributeToJsp(COURSE_ID_REQUEST_PARAM_NAME, courseId);
        request.addAttributeToJsp(USER_ID_REQUEST_PARAM_NAME, userId);
        request.addAttributeToJsp(TASK_ID_REQUEST_PARAM_NAME, taskId);
        return requestFactory.createForwardResponse(propertyContext.get(GIVE_FEEDBACK_PAGE));
    }
}
