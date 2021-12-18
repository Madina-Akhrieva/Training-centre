package com.epam.jwd.onlinetraining.controller.command.common;

public interface CommandResponse {

    boolean isRedirect();

    String getPath();
}
