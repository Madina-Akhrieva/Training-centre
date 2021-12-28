package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.WrongMailException;
import com.epam.jwd.onlinetraining.service.exception.WrongPasswordException;
import com.google.protobuf.ServiceException;

public class AccountValidator {

    public static final String MAIL_CONTAINS_FORBIDDEN_CHARACTERS_MESSAGE = "Mail contains forbidden characters";
    private static final String MIN_MAIL_LENGTH_EXCEPTION_MESSAGE = "Mail length is less than 7";
    private static final String MAX_MAIL_LENGTH_EXCEPTION_MESSAGE = "Mail length is more than 15";
    private static final String MIN_PASS_LENGTH_EXCEPTION_MESSAGE = "Password length is less than 7";
    private static final String MAX_PASS_LENGTH_EXCEPTION_MESSAGE = "Password length is more than 15";

    private final static Integer MIN_EMAIL_LENGTH = 7;
    private final static Integer MAX_NAME_LENGTH = 15;
    private static final String MAIL_PATTERN = "/^\\S+@\\S+\\.\\S+$/";

    private void validateMail(String email) throws WrongMailException {
        if (email.length() < MIN_EMAIL_LENGTH) {
            throw new WrongMailException(MIN_MAIL_LENGTH_EXCEPTION_MESSAGE);
        }
        if (email.length() > MAX_NAME_LENGTH) {
            throw new WrongMailException(MAX_MAIL_LENGTH_EXCEPTION_MESSAGE);
        }
        if (!email.matches(MAIL_PATTERN)){
            throw new WrongMailException(MAIL_CONTAINS_FORBIDDEN_CHARACTERS_MESSAGE);
        }
    }

    private void validatePassword(String password) throws WrongPasswordException {
        if (password.length() < MIN_EMAIL_LENGTH) {
            throw new WrongPasswordException(MIN_PASS_LENGTH_EXCEPTION_MESSAGE);
        }
        if (password.length() > MAX_NAME_LENGTH) {
            throw new WrongPasswordException(MAX_PASS_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    private void validatePhone(String phoneNumber) throws WrongPasswordException {
        if (phoneNumber.length() < MIN_EMAIL_LENGTH) {
            throw new WrongPasswordException(MIN_PASS_LENGTH_EXCEPTION_MESSAGE);
        }
        if (phoneNumber.length() > MAX_NAME_LENGTH) {
            throw new WrongPasswordException(MAX_PASS_LENGTH_EXCEPTION_MESSAGE);
        }
    }
}
