package com.epam.jwd.onlinetraining.controller.command;

public interface CommandResponse {
    Boolean isRedirect();
    String getPath();
}
