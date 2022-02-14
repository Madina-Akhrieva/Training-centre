package com.epam.jwd.onlinetraining.controller.command.common;

import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.controller.command.common public interface CommandRequest
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface CommandRequest {

    void addAttributeToJsp(String name, Object attribute);

    String getParameter(String name);

    boolean sessionExists();

    boolean addToSession(String name, Object value);

    Optional<Object> retrieveFromSession(String name);

    void cleareSession();

    void createSession();


}
