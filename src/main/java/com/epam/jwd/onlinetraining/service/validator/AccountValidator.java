package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongMailException;
import com.epam.jwd.onlinetraining.service.exception.WrongPasswordException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.epam.jwd.onlinetraining.service.validator public final class AccountValidator
 * extends Object
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public final class AccountValidator {

    public static final String MAIL_CONTAINS_FORBIDDEN_CHARACTERS_MESSAGE = "Mail contains forbidden characters";
    private static final String MIN_MAIL_LENGTH_EXCEPTION_MESSAGE = "Mail length is less than 7";
    private static final String MAX_MAIL_LENGTH_EXCEPTION_MESSAGE = "Mail length is more than 50";
    private static final String MIN_PASS_LENGTH_EXCEPTION_MESSAGE = "Password length is less than 7";
    private static final String MAX_PASS_LENGTH_EXCEPTION_MESSAGE = "Password length is more than 15";
    private final static Integer MIN_EMAIL_LENGTH = 7;
    private final static Integer MAX_NAME_LENGTH = 50;
    private static final String PASSWORD_CONTAINS_FORBIDDEN_SYMBOLS = "Password contains forbidden symbols";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^[a-zA-Z0-9]+$", Pattern.CASE_INSENSITIVE);
    private static final int MAX_PASSWORD_LENGTH = 15;
    public static final String EMPTY_EXCEPTION_IS_CAUGHT_MESSAGE = "Empty exception is caught";
    public static final String EMPTY_STRING = "";

    public static AccountValidator getInstance() {
        return AccountValidator.Holder.INSTANCE;
    }

    public void validateMail(String email) throws WrongMailException, EmptyInputException {
        if (email == EMPTY_STRING) {
            throw new EmptyInputException(EMPTY_EXCEPTION_IS_CAUGHT_MESSAGE);
        }
        if (email.length() < MIN_EMAIL_LENGTH) {
            throw new WrongMailException(MIN_MAIL_LENGTH_EXCEPTION_MESSAGE);
        }
        if (email.length() > MAX_NAME_LENGTH) {
            throw new WrongMailException(MAX_MAIL_LENGTH_EXCEPTION_MESSAGE);
        }
        if (!matchMailPattern(email)) {
            throw new WrongMailException(MAIL_CONTAINS_FORBIDDEN_CHARACTERS_MESSAGE);
        }
    }

    public static boolean matchMailPattern(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }


    public void validatePassword(String password) throws WrongPasswordException, EmptyInputException {
        if (password == EMPTY_STRING) {
            throw new EmptyInputException(EMPTY_EXCEPTION_IS_CAUGHT_MESSAGE);
        }
        if (password.length() < MIN_EMAIL_LENGTH) {
            throw new WrongPasswordException(MIN_PASS_LENGTH_EXCEPTION_MESSAGE);
        }
        if (password.length() > MAX_PASSWORD_LENGTH) {
            throw new WrongPasswordException(MAX_PASS_LENGTH_EXCEPTION_MESSAGE);
        }
        if (!matchPasswordPattern(password)) {
            throw new WrongPasswordException(PASSWORD_CONTAINS_FORBIDDEN_SYMBOLS);
        }
    }

    public boolean matchPasswordPattern(String passwordString) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordString);
        return matcher.find();
    }

    private static class Holder {
        public static final AccountValidator INSTANCE = new AccountValidator();
    }


}
