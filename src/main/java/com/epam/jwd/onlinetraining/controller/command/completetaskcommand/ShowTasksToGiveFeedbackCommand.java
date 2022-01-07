package com.epam.jwd.onlinetraining.controller.command.completetaskcommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.TaskService;

import java.util.List;

public enum ShowTasksToGiveFeedbackCommand implements Command {
    INSTANCE(ServiceFactory.simple().taskService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final String ID_REQUEST_PARAM_NAME = "id";
    private static final String LEAVE_FEEDBACK = "page.tasks_with_feedback";
    private static final String COURSE_ID_PARAM = "id";
    private static final String TASKS_ATTRIBUTE_NAME = "tasks";
    private static final String ID_COURSE_REQUEST_PARAM_NAME = "course_id";
    private static final String ID_TASK_REQUEST_PARAM_NAME = "task_id";
    private static final String USER_ID_REQUEST_PARAM_NAME = "uid";

    private final TaskService taskService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowTasksToGiveFeedbackCommand(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.taskService = taskService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        long courseId = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
        long userId = Long.parseLong(request.getParameter(USER_ID_REQUEST_PARAM_NAME));
//        final List<Task> tasks = taskService.findAll(courseId);
        final List<Task> tasks = taskService.findAllTasksByCourseIdAndUserId(courseId, userId);
        request.addAttributeToJsp(COURSE_ID_PARAM, courseId);
        request.addAttributeToJsp(TASKS_ATTRIBUTE_NAME, tasks);

        return requestFactory.createForwardResponse(propertyContext.get(LEAVE_FEEDBACK));
    }
}
