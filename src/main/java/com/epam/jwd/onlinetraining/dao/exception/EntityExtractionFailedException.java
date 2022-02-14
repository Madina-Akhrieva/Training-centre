package com.epam.jwd.onlinetraining.dao.exception;

/**
 * com.epam.jwd.onlinetraining.dao.exception public class EntityExtractionFailedException
 * extends Exception
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class EntityExtractionFailedException extends Exception {

    public EntityExtractionFailedException(String message) {
        super(message);
    }

    public EntityExtractionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
