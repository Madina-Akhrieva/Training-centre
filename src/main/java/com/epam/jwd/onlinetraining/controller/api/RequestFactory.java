package com.epam.jwd.onlinetraining.controller.api;

import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.core.SimpleRequestFactory;

import javax.servlet.http.HttpServletRequest;

public interface RequestFactory {

    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createRedirectResponse(String path);

    CommandResponse createForwardResponse(String path);

    static RequestFactory getInstance() {
        return SimpleRequestFactory.INSTANCE;
    }

}
