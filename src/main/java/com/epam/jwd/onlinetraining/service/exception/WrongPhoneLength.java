package com.epam.jwd.onlinetraining.service.exception;

/**
 * com.epam.jwd.onlinetraining.service.exception public class WrongPhoneLength
 * extends Throwable
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class WrongPhoneLength extends Throwable {

    public WrongPhoneLength(String wrongPhoneLengthMessage) {
        super(wrongPhoneLengthMessage);
    }

    public WrongPhoneLength(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongPhoneLength() {
    }
}
