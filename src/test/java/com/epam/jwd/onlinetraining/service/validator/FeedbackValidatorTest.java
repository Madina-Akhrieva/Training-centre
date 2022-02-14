package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongFeedbackException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.epam.jwd.onlinetraining.service.validator.FeedbackValidator.EMPTY_INPUT_EXCEPTION_MESSAGE;
import static com.epam.jwd.onlinetraining.service.validator.CourseValidator.EMPTY_STRING;
import static com.epam.jwd.onlinetraining.service.validator.FeedbackValidator.MIN_FEEDBACK_LENGTH_EXCEPTION_MESSAGE;

class FeedbackValidatorTest {
    private static final String SHORT_FEEDBACK = "a";
    private static FeedbackValidator feedbackValidator;

    @BeforeAll
    public static void createFeedbackValidator() {
        feedbackValidator = new FeedbackValidator();
    }

    @Test
    public void ShouldThrowWrongFeedbackExceptionWhenFeedbackIsShort() {
        WrongFeedbackException thrown = Assertions.assertThrows(WrongFeedbackException.class, () -> {
            feedbackValidator.validateFeedback(SHORT_FEEDBACK);
        }, MIN_FEEDBACK_LENGTH_EXCEPTION_MESSAGE);

        Assertions.assertEquals(MIN_FEEDBACK_LENGTH_EXCEPTION_MESSAGE, thrown.getMessage());
    }


    @Test
    public void ShouldThrowWrongFeedbackExceptionWhenFeedbackIsEmpty() {
        EmptyInputException thrown = Assertions.assertThrows(EmptyInputException.class, () -> {
            feedbackValidator.validateFeedback(EMPTY_STRING);
        }, EMPTY_INPUT_EXCEPTION_MESSAGE);

        Assertions.assertEquals(EMPTY_INPUT_EXCEPTION_MESSAGE, thrown.getMessage());
    }


}