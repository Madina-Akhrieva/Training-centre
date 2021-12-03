package com.epam.jwd.onlinetraining.controller.command;

public interface CommandResponse {

    boolean isRedirect();

    String getPath();
}
