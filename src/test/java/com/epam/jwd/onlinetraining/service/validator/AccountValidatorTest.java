package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.WrongMailException;
import com.epam.jwd.onlinetraining.service.exception.WrongPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AccountValidatorTest {
    private static final String MAIL_STRING = "test_mail";
    private static final String WRONG_MAIL_EXCEPTION_WAS_EXPECTED_MESSAGE = "WrongMailException was expected";
    private static final String EXPECTED_MESSAGE = "Mail contains forbidden characters";
    public static final String PASSWORD_WHICH_LENGTH_IS_MORE_THAN_FIFTEEN = "655656568888888888888888888888";
    public static final String PASSWORD_WHICH_LENGTH_IS_LESS_THAN_SEVEN = "65565";
    public static final String PASSWORD_LENGTH_IS_LESS_THAN_SEVEN_MESSAGE = "Password length is less than 7";
    private static final String MAX_PASS_LENGTH_EXCEPTION_MESSAGE = "Password length is more than 15";
    private static final String MAX_MAIL_LENGTH_EXCEPTION_MESSAGE = "Mail length is more than 50";
    public static final String LONG_MAIL_STRING = "lsdfjsdlksksldkfnsllfddsknslkdnfsldknfsdcvldfvndflkkn@mail.com";

    private static AccountValidator accountValidator;

    @BeforeAll
    public static void createAccountValidator() {
        accountValidator = new AccountValidator();
    }

    @Test
    public void ShouldThrowWrongMailExceptionWhenMailIsWrong() {
        WrongMailException thrown = Assertions.assertThrows(WrongMailException.class, () -> {
            accountValidator.validateMail(MAIL_STRING);
        }, WRONG_MAIL_EXCEPTION_WAS_EXPECTED_MESSAGE);

        Assertions.assertEquals(EXPECTED_MESSAGE, thrown.getMessage());
    }

    @Test
    public void ShouldThrowWrongMailExceptionWhenMailLengthIsMoreThanFifty() {
        WrongMailException thrown = Assertions.assertThrows(WrongMailException.class, () -> {
            accountValidator.validateMail(LONG_MAIL_STRING);
        }, WRONG_MAIL_EXCEPTION_WAS_EXPECTED_MESSAGE);

        Assertions.assertEquals(MAX_MAIL_LENGTH_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    @Test
    public void ShouldThrowWrongPasswordExceptionWhenPasswordLengthIsLessThanSeven() {
        WrongPasswordException thrown = Assertions.assertThrows(WrongPasswordException.class, () -> {
            accountValidator.validatePassword(PASSWORD_WHICH_LENGTH_IS_LESS_THAN_SEVEN);
        }, WRONG_MAIL_EXCEPTION_WAS_EXPECTED_MESSAGE);
        Assertions.assertEquals(PASSWORD_LENGTH_IS_LESS_THAN_SEVEN_MESSAGE, thrown.getMessage());
    }

    @Test
    public void ShouldThrowWrongPasswordExceptionWhenPasswordLengthIsMoreThanFifteen() {
        WrongPasswordException thrown = Assertions.assertThrows(WrongPasswordException.class, () -> {
            accountValidator.validatePassword(PASSWORD_WHICH_LENGTH_IS_MORE_THAN_FIFTEEN);
        }, WRONG_MAIL_EXCEPTION_WAS_EXPECTED_MESSAGE);
        Assertions.assertEquals(MAX_PASS_LENGTH_EXCEPTION_MESSAGE, thrown.getMessage());
    }


}