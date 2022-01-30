package com.epam.jwd.onlinetraining.service.exception;

public class WrongFirstNameException extends Throwable {
    public WrongFirstNameException() {
    }

    public WrongFirstNameException(String wrongPhoneExceptionMessage) {
    }

    public WrongFirstNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
