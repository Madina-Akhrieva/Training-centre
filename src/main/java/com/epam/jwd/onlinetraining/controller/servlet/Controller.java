package com.epam.jwd.onlinetraining.controller.servlet;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.Command;
import com.epam.jwd.onlinetraining.controller.command.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.CommandResponse;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public static final String COMMAND_PARAM_NAME = "command";


    private final RequestFactory requestFactory = RequestFactory.getInstance();

    @Override
    public void init() {
        ConnectionPool.instance().init();
    }

    @Override
    public void destroy() {
        ConnectionPool.instance().shutDown();
    }

    @Override
    protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        LOGGER.trace("caught req and resp in doGet method");
        processRequest(httpRequest, httpResponse);
     }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        LOGGER.trace("caught req and resp in doPost method");
        processRequest(httpRequest, httpResponse);
    }

    private void processRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        final String commandName = httpRequest.getParameter(COMMAND_PARAM_NAME);
        final Command command = Command.of(commandName);
        final CommandRequest commandRequest = requestFactory.createRequest(httpRequest);
        final CommandResponse commandResponse = command.execute(commandRequest);
        proceedWithResponse(httpRequest, httpResponse, commandResponse);
    }

    private void proceedWithResponse(HttpServletRequest request, HttpServletResponse response,
                                     CommandResponse commandResponse) {
        try {
            LOGGER.debug("Entered to proceedWithResponse method ");
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