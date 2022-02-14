package com.epam.jwd.onlinetraining.service.exception;

/**
 * com.epam.jwd.onlinetraining.service.exception public class WrongLinkException
 * extends Exception
 *
 *  @author Madina Akhrieva
 *  @version 1.0
 */
public class WrongLinkException extends Exception {

    public WrongLinkException(String message) {
        super(message);
    }

    public WrongLinkException(String message, Throwable cause) {
        super(message, cause);
    }
}
