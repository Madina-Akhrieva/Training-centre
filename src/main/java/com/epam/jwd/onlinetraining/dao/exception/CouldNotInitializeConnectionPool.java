package com.epam.jwd.onlinetraining.dao.exception;

public class CouldNotInitializeConnectionPool extends Exception {

    public CouldNotInitializeConnectionPool(String message) {
        super(message);
    }

    public CouldNotInitializeConnectionPool(String message, Throwable cause) {
        super(message, cause);
    }
}
