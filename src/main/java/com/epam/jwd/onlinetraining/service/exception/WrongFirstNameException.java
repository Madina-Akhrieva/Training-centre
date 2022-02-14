package com.epam.jwd.onlinetraining.service.exception;

/**
 * com.epam.jwd.onlinetraining.service.exception public class WrongFirstNameException
 * extends Throwable
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class WrongFirstNameException extends Throwable {

    public WrongFirstNameException(String message) {
        super(message);
    }

    public WrongFirstNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
