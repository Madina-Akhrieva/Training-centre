package com.epam.jwd.onlinetraining.controller.command.taskscommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;


/**
 * com.epam.jwd.onlinetraining.controller.command.taskscommand public enum CheckTaskCommand
 * extends Enum<CheckTaskCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum CheckTaskCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String ADD_COURSE = "page.check_task";
    private static final String INDEX_JSP_PATH = "page.index";


    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    CheckTaskCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            return requestFactory.createForwardResponse(propertyContext.get(ADD_COURSE));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }
    }
}
