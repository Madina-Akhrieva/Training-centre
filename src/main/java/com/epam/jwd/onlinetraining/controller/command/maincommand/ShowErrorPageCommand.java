package com.epam.jwd.onlinetraining.controller.command.maincommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;

/**
 * com.epam.jwd.onlinetraining.controller.command.maincommand public enum ShowErrorPageCommand
 * extends Enum<ShowErrorPageCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum ShowErrorPageCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String INDEX_JSP_PATH = "page.index";

    private static final String ERROR_PAGE = "page.error";
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowErrorPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }
    }
}
