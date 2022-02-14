package com.epam.jwd.onlinetraining.service.exception;

/**
 * com.epam.jwd.onlinetraining.service.exception public class WrongFeedbackException
 * extends Exception
 *
 *  @author Madina Akhrieva
 *  @version 1.0
 */
public class WrongFeedbackException extends Exception {

    public WrongFeedbackException() {
        super();
    }

    public WrongFeedbackException(String message) {
        super(message);
    }

    public WrongFeedbackException(String message, Throwable cause) {
        super(message, cause);
    }
}
