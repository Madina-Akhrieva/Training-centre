package com.epam.jwd.onlinetraining.service.exception;

/**
 * com.epam.jwd.onlinetraining.service.exception public class WrongPasswordException
 * extends Exception
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class WrongPhoneException extends Throwable {


    public WrongPhoneException(String wrongPhoneExceptionMessage) {
        super(wrongPhoneExceptionMessage);
    }

    public WrongPhoneException(String message, Throwable cause) {
        super(message, cause);
    }
}
