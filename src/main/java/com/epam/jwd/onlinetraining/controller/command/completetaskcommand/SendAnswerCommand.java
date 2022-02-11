package com.epam.jwd.onlinetraining.controller.command.completetaskcommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.api.CourseService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.TaskService;
import com.epam.jwd.onlinetraining.service.exception.WrongLinkException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public enum SendAnswerCommand implements Command {
    INSTANCE(ServiceFactory.simple().taskService(), RequestFactory.getInstance(), PropertyContext.instance());
    private static final Logger LOGGER = LogManager.getLogger(SendAnswerCommand.class);

    private static final String COURSE_ID_REQUEST_PARAM_NAME = "course_id";
    private static final String USER_ID_REQUEST_PARAM_NAME = "user_id";
    private static final String TASK_ID_REQUEST_PARAM_NAME = "task_id";
    private static final String LINK_ANSWER_REQUEST_PARAM_NAME = "answer";
    private static final String COMPLETE_TASK = "page.add_answer";
    private static final String COURSE_ID_PARAM = "course_id";
    private static final String TASKS_ATTRIBUTE_NAME = "tasks";
    private static final String WRONG_LINK_ATTRIBUTE = "wrongLinkAttribute";
    private static final String WRONG_LINK_MESSAGE = "Check link to answer once more please ♥";
    private static final String SUCCESSFUL_ADD_ATTRIBUTE = "successfulAddMessage";
    private static final String SUCCESSFUL_ADD_MESSAGE_TEXT = "Answer is successfully added ♥";

    private final TaskService taskService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    SendAnswerCommand(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.taskService = taskService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final long courseId = Long.parseLong(request.getParameter(COURSE_ID_REQUEST_PARAM_NAME));
            final long userId = Long.parseLong(request.getParameter(USER_ID_REQUEST_PARAM_NAME));
            final long taskId = Long.parseLong(request.getParameter(TASK_ID_REQUEST_PARAM_NAME));
            request.addToSession(TASK_ID_REQUEST_PARAM_NAME, taskId);
            request.addToSession(COURSE_ID_PARAM, courseId);
            final String answer = request.getParameter(LINK_ANSWER_REQUEST_PARAM_NAME);
            taskService.addTaskAnswerToUser(answer, userId, courseId, taskId);
            final List<Task> tasks = taskService.findAll(courseId);
            request.addToSession(TASKS_ATTRIBUTE_NAME, tasks);
        } catch (WrongLinkException e) {
            LOGGER.warn("WrongLinkException is caught");
            request.addToSession(WRONG_LINK_ATTRIBUTE, WRONG_LINK_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(COMPLETE_TASK));
        }
        request.addToSession(SUCCESSFUL_ADD_ATTRIBUTE, SUCCESSFUL_ADD_MESSAGE_TEXT);
        return requestFactory.createRedirectResponse(propertyContext.get(COMPLETE_TASK));
    }
}
