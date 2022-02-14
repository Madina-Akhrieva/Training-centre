package com.epam.jwd.onlinetraining.controller.command.taskscommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Task;
import com.epam.jwd.onlinetraining.service.api.TaskService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.TaskService;
import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongLinkException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;


/**
 * com.epam.jwd.onlinetraining.controller.command.taskscommand public enum AddTaskCommand
 * extends Enum<AddTaskCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum AddTaskCommand implements Command {
    INSTANCE(ServiceFactory.simple().taskService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(com.epam.jwd.onlinetraining.controller.command.coursecomamnd.AddCourseCommand.class);
    private static final String ADD_TASK_JSP_PAGE = "page.add_task";
    private static final String WRONG_LINK_ATTRIBUTE = "wrongLinkAttribute";
    private static final String WRONG_LINK_MESSAGE = "Entered expression is not the url. Check once more please ♥";
    private static final String WRONG_TITLE_ATTRIBUTE = "wrongTitleAttribute";
    private static final String WRONG_TITLE_MESSAGE = "Title contains wrong symbols. Check once more please ♥";
    private static final String ID_REQUEST_PARAM_NAME = "id";
    private static final String INDEX_JSP_PATH = "page.index";

    private static final String COURSE_ID_REQUEST_ATTRIBUTE_NAME = "courseId";
    private static final String WATCH_TASKS = "page.manage_tasks";
    private static final String TASKS_ATTRIBUTE_NAME = "tasks";
    private static final String SUCCESSFUL_ADD_ATTRIBUTE = "successfulSignupMessage";
    private static final String SUCCESSFUL_ADD_MESSAGE_TEXT = "Task is added successfully ♥";
    private static final String EMPTY_INPUTS_ATTRIBUTE = "emptyInputsMessage";
    private static final Object EMPTY_INPUTS_MESSAGE_TEXT = "None input is allowed to be empty!";
    public static final String TITLE_ATTRIBUTE_NAME = "title";

    private final TaskService taskService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    AddTaskCommand(TaskService taskService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.taskService = taskService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            LOGGER.info("We are in execute method in AddCourseCommand");
            long courseId = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
            final String title = request.getParameter(TITLE_ATTRIBUTE_NAME);
            final String description = request.getParameter("description");
            request.addToSession(COURSE_ID_REQUEST_ATTRIBUTE_NAME, courseId);
            taskService.addTaskToCourse(new Task(courseId, title, description), courseId);
            final List<Task> tasks = taskService.findAll(courseId);
            request.addAttributeToJsp(TASKS_ATTRIBUTE_NAME, tasks);
        } catch (WrongTitleException e) {
            LOGGER.warn("Entered title is wrong.");
            request.addToSession(WRONG_TITLE_ATTRIBUTE, WRONG_TITLE_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(ADD_TASK_JSP_PAGE));
        } catch (WrongLinkException e) {
            LOGGER.warn("Entered link is wrong.");
            request.addToSession(WRONG_LINK_ATTRIBUTE, WRONG_LINK_MESSAGE);
            return requestFactory.createRedirectResponse(propertyContext.get(ADD_TASK_JSP_PAGE));
        } catch (EmptyInputException e) {
            LOGGER.warn("Empty inputs exception");
            request.addAttributeToJsp(EMPTY_INPUTS_ATTRIBUTE, EMPTY_INPUTS_MESSAGE_TEXT);
            request.addToSession(EMPTY_INPUTS_ATTRIBUTE, EMPTY_INPUTS_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(ADD_TASK_JSP_PAGE));
        }catch (Exception exception){
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }
        request.addToSession(SUCCESSFUL_ADD_ATTRIBUTE, SUCCESSFUL_ADD_MESSAGE_TEXT);
        return requestFactory.createForwardResponse(propertyContext.get(WATCH_TASKS));
    }
}