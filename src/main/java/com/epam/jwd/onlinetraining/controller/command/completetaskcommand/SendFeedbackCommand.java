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

public enum SendFeedbackCommand implements Command {
    INSTANCE(ServiceFactory.simple().taskService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(SendAnswerCommand.class);
    private static final String COURSE_ID_REQUEST_PARAM_NAME = "course_id";
    private static final String USER_ID_REQUEST_PARAM_NAME = "user_id";
    private static final String TASK_ID_REQUEST_PARAM_NAME = "task_id";
    private static final String FEEDBACK_REQUEST_PARAM_NAME = "feedback";
    private static final String MAIN_PAGE = "page.main";

    private final TaskService taskService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    SendFeedbackCommand(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.taskService = taskService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final long courseId = Long.parseLong(request.getParameter(COURSE_ID_REQUEST_PARAM_NAME));
        final long userId = Long.parseLong(request.getParameter(USER_ID_REQUEST_PARAM_NAME));
        final long taskId = Long.parseLong(request.getParameter(TASK_ID_REQUEST_PARAM_NAME));
        final String feedback = request.getParameter(FEEDBACK_REQUEST_PARAM_NAME);

        if (taskService.addFeedbackToAnswer(feedback, userId, taskId)) {
            LOGGER.info("Feedback is added");
            //todo:add message that feedback is added
        } else {
            LOGGER.info("Feedback is not added");
            //todo:add message that feedback is not added
        }
        return requestFactory.createForwardResponse(propertyContext.get(MAIN_PAGE));
    }


}
