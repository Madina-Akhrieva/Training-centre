package com.epam.jwd.onlinetraining.service.exception;

public class WrongPhoneLength extends Throwable {
    public WrongPhoneLength(String wrongPhoneLengthMessage) {
    }

    public WrongPhoneLength(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPhoneLength() {
    }
}
