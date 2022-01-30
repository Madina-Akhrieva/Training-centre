package com.epam.jwd.onlinetraining.service.exception;

public class AccountWithSuchEmailExists extends Throwable {
    public AccountWithSuchEmailExists(String message) {
        super(message);
    }

    public AccountWithSuchEmailExists(String message, Throwable cause) {
        super(message, cause);
    }
}
