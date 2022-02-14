package com.epam.jwd.onlinetraining.service.exception;

/**
 * com.epam.jwd.onlinetraining.service.exception public class EmptyInputException
 * extends Throwable
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class EmptyInputException extends Throwable {

    public EmptyInputException(String message) {
        super(message);
    }

    public EmptyInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
