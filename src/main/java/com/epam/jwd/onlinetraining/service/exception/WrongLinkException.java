package com.epam.jwd.onlinetraining.service.exception;

public class WrongLinkException extends Exception {
    public WrongLinkException() {
        super();
    }

    public WrongLinkException(String message) {
        super(message);
    }

    public WrongLinkException(String message, Throwable cause) {
        super(message, cause);
    }
}
