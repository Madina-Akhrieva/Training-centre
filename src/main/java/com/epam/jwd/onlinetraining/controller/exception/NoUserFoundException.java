package com.epam.jwd.onlinetraining.controller.exception;

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
