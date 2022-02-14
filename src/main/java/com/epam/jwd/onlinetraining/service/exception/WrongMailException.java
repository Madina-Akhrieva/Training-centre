package com.epam.jwd.onlinetraining.service.exception;

/**
 * com.epam.jwd.onlinetraining.service.exception public class WrongMailException
 * extends Exception
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class WrongMailException extends Exception {

    public WrongMailException() {
    }

    public WrongMailException(String message) {
        super(message);
    }

    public WrongMailException(String message, Throwable cause) {
        super(message, cause);
    }


    public WrongMailException(Throwable cause) {
        super(cause);
    }

    public WrongMailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
