package com.epam.jwd.onlinetraining.controller.api;

import com.epam.jwd.onlinetraining.controller.command.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.CommandRequestImpl;
import com.epam.jwd.onlinetraining.controller.command.CommandResponseImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum RequestFactoryImpl implements RequestFactory {
    INSTANCE;

    private final Map<String, CommandResponse> forwardResponseCache = new ConcurrentHashMap<>();
    private final Map<String, CommandResponse> redirectResponseCache = new ConcurrentHashMap<>();

    @Override
    public CommandRequest createRequest(HttpServletRequest request) {
        return new CommandRequestImpl(request);
    }

    @Override
    public CommandResponse createForwardResponse(String path) {
        return forwardResponseCache.computeIfAbsent(path, CommandResponseImpl::new);
    }

//    @Override
//    public CommandResponse createRedirectResponse(String path) {
//        return redirectResponseCache.computeIfAbsent(path, p -> new PlainCommandResponse(true, p));
//    }
}
