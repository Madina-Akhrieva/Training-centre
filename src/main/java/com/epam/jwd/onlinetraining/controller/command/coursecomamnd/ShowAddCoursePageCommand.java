package com.epam.jwd.onlinetraining.controller.command.coursecomamnd;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;

/**
 * com.epam.jwd.onlinetraining.controller.command.coursecomamnd public enum ShowAddCoursePageCommand
 * extends Enum<ShowAddCoursePageCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum ShowAddCoursePageCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String ADD_COURSE = "page.add_course";
    private static final String INDEX_JSP_PATH = "page.index";


    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAddCoursePageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
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
