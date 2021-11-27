package com.epam.jwd.onlinetraining.controller.impl;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.Command;
import com.epam.jwd.onlinetraining.controller.command.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.CommandResponse;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    public static final String COMMAND_PARAM_NAME = "command";
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    private final RequestFactory requestFactory = RequestFactory.getInstance();

    @Override
    public void init(){
        ConnectionPoolImpl.getInstance().init();
    }

    @Override
    public void destroy() {
        ConnectionPoolImpl.getInstance().shutdown();
    }

    @Override
    protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        LOGGER.debug("caught req and resp in doGet method");

        //todo:to be deleted

        final String commandName = httpRequest.getParameter(COMMAND_PARAM_NAME);
        final Command command = Command.of(commandName);
        final CommandRequest commandRequest = requestFactory.createRequest(httpRequest);
        final CommandResponse commandResponse = command.execute(commandRequest);
        proceedWithResponse(httpRequest, httpResponse, commandResponse);
        LOGGER.debug("doGet method is finished");
    }

    private void proceedWithResponse(HttpServletRequest request, HttpServletResponse response,
                                     CommandResponse commandResponse) {
        try {
            LOGGER.debug("We entered to proceedWithResponse method ");
            forwardOrRedirectToResponseLocation(request, response, commandResponse);
        } catch (ServletException e) {
            LOGGER.error("ServletException exception occurred", e);
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error("IO exception occurred ", e);
            e.printStackTrace();
        }
    }

    private void forwardOrRedirectToResponseLocation(HttpServletRequest request, HttpServletResponse response,
                                                     CommandResponse commandResponse) throws IOException, ServletException {
        LOGGER.debug("We are in forwardOrRedirectToResponseLocation method ");
        if (commandResponse.isRedirect()) {
            response.sendRedirect(commandResponse.getPath());
        } else {
            final String desiredPath = commandResponse.getPath();
            final RequestDispatcher dispatcher = request.getRequestDispatcher(desiredPath);
            dispatcher.forward(request, response);
        }

    }
}