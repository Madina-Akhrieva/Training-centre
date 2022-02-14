package com.epam.jwd.onlinetraining.controller.command.completetaskcommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.TaskService;
import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongFeedbackException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * com.epam.jwd.onlinetraining.controller.command.completetaskcommand public enum SendFeedbackCommand
 * extends Enum<SendFeedbackCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum SendFeedbackCommand implements Command {
    INSTANCE(ServiceFactory.simple().taskService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(SendAnswerCommand.class);
    private static final String COURSE_ID_REQUEST_PARAM_NAME = "course_id";
    private static final String USER_ID_REQUEST_PARAM_NAME = "user_id";
    private static final String TASK_ID_REQUEST_PARAM_NAME = "task_id";
    private static final String INDEX_JSP_PATH = "page.index";

    private static final String FEEDBACK_REQUEST_PARAM_NAME = "feedback";
    private static final String GIVE_FEEDBACK_JSP = "page.give_feedback";
    private static final String WRONG_FEEDBACK_ATTRIBUTE = "wrongFeedbackAttribute";
    private static final Object WRONG_FEEDBACK_MESSAGE = "Feedback is wrong. Check it please ♥";
    private static final String SUCCESSFUL_ADD_ATTRIBUTE = "successfulAddMessage";
    private static final String SUCCESSFUL_ADD_MESSAGE_TEXT = "Answer is successfully added ♥";
    private static final String COURSE_ID_PARAM = "course_id";
    private static final String EMPTY_INPUTS_ATTRIBUTE = "emptyInputsMessage";
    private static final Object EMPTY_INPUTS_MESSAGE_TEXT = "None input is allowed to be empty!";

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
        try {
            final long courseId = Long.parseLong(request.getParameter(COURSE_ID_REQUEST_PARAM_NAME));
            final long userId = Long.parseLong(request.getParameter(USER_ID_REQUEST_PARAM_NAME));
            final long taskId = Long.parseLong(request.getParameter(TASK_ID_REQUEST_PARAM_NAME));
            final String feedback = request.getParameter(FEEDBACK_REQUEST_PARAM_NAME).trim();
            request.addToSession(TASK_ID_REQUEST_PARAM_NAME, taskId);
            request.addToSession(USER_ID_REQUEST_PARAM_NAME, userId);
            request.addToSession(COURSE_ID_PARAM, courseId);
            taskService.addFeedbackToAnswer(feedback, userId, taskId);
        } catch (WrongFeedbackException e) {
            LOGGER.warn("WrongFeedbackException is caught");
            request.addToSession(WRONG_FEEDBACK_ATTRIBUTE, WRONG_FEEDBACK_MESSAGE);
            return requestFactory.createForwardResponse(propertyContext.get(GIVE_FEEDBACK_JSP));
        } catch (EmptyInputException e) {
            LOGGER.warn("Empty inputs exception");
            request.addAttributeToJsp(EMPTY_INPUTS_ATTRIBUTE, EMPTY_INPUTS_MESSAGE_TEXT);
            request.addToSession(EMPTY_INPUTS_ATTRIBUTE, EMPTY_INPUTS_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(GIVE_FEEDBACK_JSP));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }
        request.addToSession(SUCCESSFUL_ADD_ATTRIBUTE, SUCCESSFUL_ADD_MESSAGE_TEXT);
        return requestFactory.createForwardResponse(propertyContext.get(GIVE_FEEDBACK_JSP));
    }


}
