package com.epam.jwd.onlinetraining.service.exception;

public class WrongPhoneException extends Throwable {
    public WrongPhoneException() {
    }

    public WrongPhoneException(String wrongPhoneExceptionMessage) {
    }

    public WrongPhoneException(String message, Throwable cause) {
        super(message, cause);
    }
}
