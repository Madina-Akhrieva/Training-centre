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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public enum AddTaskCommand implements Command {
    INSTANCE(ServiceFactory.simple().taskService(), RequestFactory.getInstance(), PropertyContext.instance());
    private static final Logger LOGGER = LogManager.getLogger(com.epam.jwd.onlinetraining.controller.command.coursecomamnd.AddCourseCommand.class);

    private static final String IF_ADDED_ATTRIBUTE = "message";
    private static final String INVALID_COURSE_MESSAGE = "Course information is invalid";
    private static final String ADD_TASK_JSP_PAGE = "page.add_task";
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
        LOGGER.info("We are in execute method in AddCourseCommand");


        long courseId = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
        final List<Task> tasks = taskService.findAll(courseId);
        final String title = request.getParameter("title");
        final String description = request.getParameter("description");

        Optional<Task> task = taskService.addTaskToCourse(new Task( courseId, title, description), courseId);


        request.addAttributeToJsp(TASKS_ATTRIBUTE_NAME, tasks);
        request.addAttributeToJsp(COURSE_ID_REQUEST_ATTRIBUTE_NAME, courseId);
        request.addAttributeToJsp(IF_NOT_ADDED_ATTRIBUTE, "task is not added");
        return requestFactory.createForwardResponse(propertyContext.get(WATCH_TASKS));

    }
}