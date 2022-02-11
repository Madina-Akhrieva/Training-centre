package com.epam.jwd.onlinetraining.controller.command.completetaskcommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.TaskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum AddTaskAnswerCommand implements Command {
    INSTANCE(ServiceFactory.simple().taskService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(com.epam.jwd.onlinetraining.controller.command.coursecomamnd.AddCourseCommand.class);
    private static final String IF_ADDED_ATTRIBUTE = "message";
    private static final String INVALID_COURSE_MESSAGE = "Course information is invalid";
    private static final String ADD_ANSWER_PAGE = "page.add_answer";
    private static final String COURSE_ID_REQUEST_PARAM_NAME = "course_id";
    private static final String USER_ID_REQUEST_PARAM_NAME = "user_id";
    private static final String TASK_ID_REQUEST_PARAM_NAME = "task_id";
    private static final String FEEDBACK_REQUEST_PARAM_NAME = "feedback";

    private final TaskService taskService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    AddTaskAnswerCommand(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.taskService = taskService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        LOGGER.info("We are in execute method in AddCourseCommand");
        final long courseId = Long.parseLong(request.getParameter(COURSE_ID_REQUEST_PARAM_NAME));
        final long userId = Long.parseLong(request.getParameter(USER_ID_REQUEST_PARAM_NAME));
        final long taskId = Long.parseLong(request.getParameter(TASK_ID_REQUEST_PARAM_NAME));
        String feedback =  taskService.findFeedbackByCourseIdUserIdAndTaskId(courseId, userId, taskId);
        request.addToSession(IF_ADDED_ATTRIBUTE, INVALID_COURSE_MESSAGE);
        request.addToSession(COURSE_ID_REQUEST_PARAM_NAME, courseId);
        request.addToSession(TASK_ID_REQUEST_PARAM_NAME, taskId);
        request.addToSession(USER_ID_REQUEST_PARAM_NAME, userId);
        request.addToSession(FEEDBACK_REQUEST_PARAM_NAME, feedback);
        return requestFactory.createForwardResponse(propertyContext.get(ADD_ANSWER_PAGE));

    }
}
