package com.epam.jwd.onlinetraining.service.exception;

/**
 * com.epam.jwd.onlinetraining.service.exception public class AccountWithSuchEmailExists
 * extends Throwable
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class AccountWithSuchEmailExists extends Throwable {

    public AccountWithSuchEmailExists(String message) {
        super(message);
    }

    public AccountWithSuchEmailExists(String message, Throwable cause) {
        super(message, cause);
    }
}
