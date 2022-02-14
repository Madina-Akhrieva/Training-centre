package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongPhoneLength;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserValidatorTest {

    private static final String WRONG_PHONE_LENGTH_MESSAGE = "Phone length is 13. Check please";
    private static final String EMPTY_INPUT_EXCEPTION_IS_CAUGHT = "EmptyInputException is caught";
    private static final String EMPTY_STRING = "";
    public static final String PHONE_NUMBER = "+37533333";

    private static UserValidator userValidator;

    @BeforeAll
    public static void createUserValidatorTest() {
        userValidator = new UserValidator();
    }

    @Test
    public void ShouldThrowEmptyInputExceptionWhenPhoneIsEmpty() {
        EmptyInputException thrown = Assertions.assertThrows(EmptyInputException.class, () -> {
            userValidator.validatePhone(EMPTY_STRING);
        }, EMPTY_INPUT_EXCEPTION_IS_CAUGHT);

        Assertions.assertEquals(EMPTY_INPUT_EXCEPTION_IS_CAUGHT, thrown.getMessage());
    }


    @Test
    public void ShouldThrowWrongPhoneLengthWhenPhoneLengthIsNotThirteen() {
        WrongPhoneLength thrown = Assertions.assertThrows(WrongPhoneLength.class, () -> {
            userValidator.validatePhone(PHONE_NUMBER);
        }, WRONG_PHONE_LENGTH_MESSAGE);

        Assertions.assertEquals(WRONG_PHONE_LENGTH_MESSAGE, thrown.getMessage());
    }

    @Test
    public void ShouldThrowWrongFirstNameExceptionWhenFirstNameIsEmpty() {
        EmptyInputException thrown = Assertions.assertThrows(EmptyInputException.class, () -> {
            userValidator.validateFirstname(EMPTY_STRING);
        }, EMPTY_INPUT_EXCEPTION_IS_CAUGHT);

        Assertions.assertEquals(EMPTY_INPUT_EXCEPTION_IS_CAUGHT, thrown.getMessage());
    }


}