package com.epam.jwd.onlinetraining.service.exception;

/**
 * com.epam.jwd.onlinetraining.service.exception public class WrongTitleException
 * extends Throwable
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class WrongTitleException extends Throwable {

    public WrongTitleException(String minMailLengthExceptionMessage) {
        super(minMailLengthExceptionMessage);
    }
}
