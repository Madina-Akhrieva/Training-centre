package com.epam.jwd.onlinetraining.controller.exception;

/**
 * com.epam.jwd.onlinetraining.controller.exception public class NoUserFoundException
 * extends Throwable
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class NoUserFoundException extends Throwable {
    public NoUserFoundException() {
    }

    public NoUserFoundException(String message) {
        super(message);
    }

    public NoUserFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
