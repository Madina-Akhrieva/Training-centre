package com.epam.jwd.onlinetraining.controller.command.common;

/**
 * com.epam.jwd.onlinetraining.controller.command.common public interface CommandResponse
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface CommandResponse {

    boolean isRedirect();

    String getPath();
}
