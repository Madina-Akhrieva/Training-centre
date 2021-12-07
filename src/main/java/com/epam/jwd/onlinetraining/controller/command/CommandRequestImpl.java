package com.epam.jwd.onlinetraining.controller.command;

import javax.servlet.http.HttpServletRequest;

public class CommandRequestImpl implements CommandRequest {

    private final HttpServletRequest request;

    public CommandRequestImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void addAttributeToJsp(String name, Object attribute) {
        request.setAttribute(name, attribute);

    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }


}
