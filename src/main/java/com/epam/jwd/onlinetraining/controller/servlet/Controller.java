package com.epam.jwd.onlinetraining.controller.servlet;

import com.epam.jwd.onlinetraining.controller.command.Command;
import com.epam.jwd.onlinetraining.controller.command.CommandResponse;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;




@WebServlet("/controller")
public class Controller extends HttpServlet {


    //метод doGet должен понимать как отркагировать на запрос клиента и понять какя команда должна отработать и
    // по этой команде и подобрать
    // соответсвующий класс для ответа пользователю и обрабатывать, значит нуден механизм с помощью которого мы будем понимать как команда должна отработать

    //мы создадим enum Command Registry в котором будут перечислены команды
    //т.е. у каждой константы будет команда которая ец соответсувует будет способ ее достать
    //здесь на каждую команду создаем константу

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String commandName = request.getParameter("command");
        final Command command = Command.of(commandName);
        final CommandResponse commandResponse = command.execute(null);
        proceedWithResponse(request, response, commandResponse);
    }

    private void proceedWithResponse(HttpServletRequest request, HttpServletResponse response,
                                     CommandResponse commandResponse) {
        try{
            forwardOrRedirectToResponseLocation(request, response, commandResponse);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void forwardOrRedirectToResponseLocation(HttpServletRequest request, HttpServletResponse response,
                                                     CommandResponse commandResponse) throws IOException, ServletException {
        if (commandResponse.isRedirect()) {
            response.sendRedirect(commandResponse.getPath());

        }else{
            final String desiredPath = commandResponse.getPath();
            final RequestDispatcher dispatcher = request.getRequestDispatcher(desiredPath);
            dispatcher.forward(request, response);
        }
    }
}