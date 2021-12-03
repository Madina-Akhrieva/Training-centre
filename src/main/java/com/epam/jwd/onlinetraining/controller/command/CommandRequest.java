package com.epam.jwd.onlinetraining.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface CommandRequest {

    void addAttributeToJsp(String name, Object attribute);

    String getParameter(String name);


}
