package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.epam.jwd.onlinetraining.service.validator.CourseValidator.EMPTY_INPUT_EXCEPTION_MESSAGE;
import static com.epam.jwd.onlinetraining.service.validator.CourseValidator.EMPTY_STRING;

class CourseValidatorTest {
    private static final String MIN_TITLE_LENGTH_EXCEPTION_MESSAGE = "Title length is less than 2";
    private static final String MAX_TITLE_LENGTH_EXCEPTION_MESSAGE = "Title length is more than 70";
    public static final String SHORT_TITLE = "f";
    private static final String LONG_TITLE = "JSVHJSV HJCSHDCS JSHCVJ HJCSVSJHCVSJDHCVSJHVC SJHCVSJHCVSJ";
    private static CourseValidator courseValidator;

    @BeforeAll
    public static void createCourseValidator() {
        courseValidator = new CourseValidator();
    }

    @Test
    public void ShouldThrowWrongTitleExceptionWhenTitleIsLong() {
        WrongTitleException thrown = Assertions.assertThrows(WrongTitleException.class, () -> {
            courseValidator.validateTitle(LONG_TITLE);
        }, MAX_TITLE_LENGTH_EXCEPTION_MESSAGE);

        Assertions.assertEquals(MAX_TITLE_LENGTH_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    @Test
    public void ShouldThrowWrongTitleExceptionWhenTitleIsShort() {
        WrongTitleException thrown = Assertions.assertThrows(WrongTitleException.class, () -> {
            courseValidator.validateTitle(SHORT_TITLE);
        }, MIN_TITLE_LENGTH_EXCEPTION_MESSAGE);

        Assertions.assertEquals(MIN_TITLE_LENGTH_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    @Test
    public void ShouldThrowWrongTitleExceptionWhenTitleIsEmpty() {
        EmptyInputException thrown = Assertions.assertThrows(EmptyInputException.class, () -> {
            courseValidator.validateTitle(EMPTY_STRING);
        }, EMPTY_INPUT_EXCEPTION_MESSAGE);

        Assertions.assertEquals(EMPTY_INPUT_EXCEPTION_MESSAGE, thrown.getMessage());
    }


}