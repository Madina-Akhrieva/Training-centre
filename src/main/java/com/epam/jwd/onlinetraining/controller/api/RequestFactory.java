package com.epam.jwd.onlinetraining.controller.api;

import com.epam.jwd.onlinetraining.controller.command.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

public interface RequestFactory {
    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createForwardResponse(String path);

    static RequestFactory getInstance(){
        return RequestFactory.getInstance();
    }
}
