package com.epam.jwd.onlinetraining.service.exception;

/**
 * com.epam.jwd.onlinetraining.service.exception public class WrongDescriptionException
 * extends Throwable
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class WrongDescriptionException extends Throwable {

    public WrongDescriptionException(String message) {
        super(message);
    }

    public WrongDescriptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
