package com.epam.jwd.onlinetraining.controller.api;

import com.epam.jwd.onlinetraining.controller.command.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RequestFactory {
    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createForwardResponse(String path);


    static RequestFactory getInstance(){
        return SimpleRequestFactory.INSTANCE;
    }


}
