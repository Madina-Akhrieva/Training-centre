package com.epam.jwd.onlinetraining.service.exception;

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
