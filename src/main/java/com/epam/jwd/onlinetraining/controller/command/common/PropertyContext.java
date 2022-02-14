package com.epam.jwd.onlinetraining.controller.command.common;

/**
 * com.epam.jwd.onlinetraining.controller.command.common public interface PropertyContext
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface PropertyContext {

    static PropertyContext instance() {
        return SimplePropertyContext.getInstance();
    }

    String get(String name);

}
