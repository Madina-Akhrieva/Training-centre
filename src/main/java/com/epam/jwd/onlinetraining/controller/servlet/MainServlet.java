package com.epam.jwd.onlinetraining.controller.servlet;

import com.epam.jwd.onlinetraining.controller.command.Command;
import com.epam.jwd.onlinetraining.controller.command.CommandResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/controller")
public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);

    //метод doGet должен понимать как отркагировать на запрос клиента и понять какя команда должна отработать и
    // по этой команде и подобрать
    // соответсвующий класс для ответа пользователю и обрабатывать, значит нуден механизм с помощью которого мы будем понимать как команда должна отработать

    //мы создадим enum Command Registry в котором будут перечислены команды
    //т.е. у каждой константы будет команда которая ец соответсувует будет способ ее достать
    //здесь на каждую команду создаем константу

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("caught req and resp in doGet method");
        final String commandName = request.getParameter("command");
        final Command command = Command.of(commandName);
        final CommandResponse commandResponse = command.execute(null);
        proceedWithResponse(request, response, commandResponse);
        LOGGER.debug("doGet method is finished");
    }

    private void proceedWithResponse(HttpServletRequest request, HttpServletResponse response,
                                     CommandResponse commandResponse) {
        try{
            LOGGER.debug("We entered to proceedWithResponse method ");
            forwardOrRedirectToResponseLocation(request, response, commandResponse);
        }catch (ServletException e){
            LOGGER.error("ServletException exception occurred", e);
            e.printStackTrace();
        }catch (IOException e){
            LOGGER.error("IO exception occurred ", e);
            e.printStackTrace();
        }
    }

    private void forwardOrRedirectToResponseLocation(HttpServletRequest request, HttpServletResponse response,
                                                     CommandResponse commandResponse) throws IOException, ServletException {
        LOGGER.debug("We are in forwardOrRedirectToResponseLocation method ");
        if (commandResponse.isRedirect()) {
            response.sendRedirect(commandResponse.getPath());

        }else{
            final String desiredPath = commandResponse.getPath();
            final RequestDispatcher dispatcher = request.getRequestDispatcher(desiredPath);
            dispatcher.forward(request, response);
        }

    }
}