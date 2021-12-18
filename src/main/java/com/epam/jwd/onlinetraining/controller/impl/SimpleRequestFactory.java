package com.epam.jwd.onlinetraining.controller.impl;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SimpleRequestFactory implements RequestFactory {

    INSTANCE;

    private final Map<String, CommandResponse> forwardResponseCache = new ConcurrentHashMap<>();
    private final Map<String, CommandResponse> redirectResponseCache = new ConcurrentHashMap<>();

    @Override
    public CommandRequest createRequest(HttpServletRequest request) {
        return new WrappingCommandRequest(request);
    }

    @Override
    public CommandResponse createForwardResponse(String path) {
        return forwardResponseCache.computeIfAbsent(path, PlainCommandResponse::new);
    }

    @Override
    public CommandResponse createRedirectResponse(String path) {
        return redirectResponseCache.computeIfAbsent(path, p -> new PlainCommandResponse(true, p));
    }
}
