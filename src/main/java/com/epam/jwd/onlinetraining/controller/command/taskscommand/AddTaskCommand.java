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
import com.epam.jwd.onlinetraining.service.exception.WrongLinkException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public enum AddTaskCommand implements Command {
    INSTANCE(ServiceFactory.simple().taskService(), RequestFactory.getInstance(), PropertyContext.instance());
    private static final Logger LOGGER = LogManager.getLogger(com.epam.jwd.onlinetraining.controller.command.coursecomamnd.AddCourseCommand.class);


    private static final String ADD_TASK_JSP_PAGE = "page.add_task";
    private static final String WRONG_LINK_ATTRIBUTE = "wrongLinkAttribute";
    private static final String WRONG_LINK_MESSAGE = "Entered expression is not the url. Check once more please ♥";
    private static final String WRONG_TITLE_ATTRIBUTE = "wrongTitleAttribute";
    private static final String WRONG_TITLE_MESSAGE = "Title contains wrong symbols. Check once more please ♥";
    private static final String ID_REQUEST_PARAM_NAME = "id";
    private static final String IF_NOT_ADDED_ATTRIBUTE = "notAddedMessge";
    private static final String COURSE_ID_REQUEST_ATTRIBUTE_NAME = "courseId";
    private static final String WATCH_TASKS = "page.manage_tasks";
    private static final String TASKS_ATTRIBUTE_NAME = "tasks";


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
            final String title = request.getParameter("title");
            final String description = request.getParameter("description");
            request.addAttributeToJsp(COURSE_ID_REQUEST_ATTRIBUTE_NAME, courseId);
            taskService.addTaskToCourse(new Task(courseId, title, description), courseId);
            final List<Task> tasks = taskService.findAll(courseId);
            request.addAttributeToJsp(TASKS_ATTRIBUTE_NAME, tasks);
            request.addAttributeToJsp(IF_NOT_ADDED_ATTRIBUTE, "task is not added");
        } catch (WrongLinkException e) {
            LOGGER.warn("Entered link is wrong.");
            request.addAttributeToJsp(WRONG_LINK_ATTRIBUTE, WRONG_LINK_MESSAGE);
            return requestFactory.createForwardResponse(propertyContext.get(ADD_TASK_JSP_PAGE));
        } catch (WrongTitleException e) {
            LOGGER.warn("Entered title is wrong.");
            request.addAttributeToJsp(WRONG_TITLE_ATTRIBUTE, WRONG_TITLE_MESSAGE);
            return requestFactory.createForwardResponse(propertyContext.get(ADD_TASK_JSP_PAGE));
        }
        return requestFactory.createForwardResponse(propertyContext.get(WATCH_TASKS));

    }
}