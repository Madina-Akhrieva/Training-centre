package com.epam.jwd.onlinetraining.controller.command.taskscommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;


/**
 * com.epam.jwd.onlinetraining.controller.command.taskscommand public enum ShowAddTaskPageCommand
 * extends Enum<ShowAddTaskPageCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum ShowAddTaskPageCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String ADD_TASK = "page.add_task";
    private static final String INDEX_JSP_PATH = "page.index";

    private static final String ID_REQUEST_PARAM_NAME = "id";
    private static final String COURSE_ID_PARAM = "courseId";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAddTaskPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            long id = Long.parseLong(request.getParameter(ID_REQUEST_PARAM_NAME));
            request.addAttributeToJsp(COURSE_ID_PARAM, id);
            return requestFactory.createForwardResponse(propertyContext.get(ADD_TASK));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }

    }
}
