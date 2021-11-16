package com.epam.jwd.onlinetraining.dao.exception;

public class NoConnectionInUsedConnections extends RuntimeException{
    public NoConnectionInUsedConnections() {
    }

    public NoConnectionInUsedConnections(String message) {
        super(message);
    }

    public NoConnectionInUsedConnections(String message, Throwable cause) {
        super(message, cause);
    }

    public NoConnectionInUsedConnections(Throwable cause) {
        super(cause);
    }

    public NoConnectionInUsedConnections(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
