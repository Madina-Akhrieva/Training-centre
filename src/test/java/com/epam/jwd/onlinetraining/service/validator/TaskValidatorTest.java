package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongLinkException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TaskValidatorTest {

    public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "EmptyInputException is caught";
    private static final String MIN_TITLE_LENGTH_EXCEPTION_MESSAGE = "Title length is less than 2";
    private static final String MIN_LINK_LENGTH_EXCEPTION_MESSAGE = "Link length is less than 2";
    public static final String EMPTY_STRING = "";
    private static final String SHORT_TITLE = "T";
    private static final String SHORT_LINK = "T";

    private static TaskValidator taskValidator;

    @BeforeAll
    public static void createTaskValidatorTest() {
        taskValidator = new TaskValidator();
    }

    @Test
    public void ShouldThrowWrongTitleExceptionWhenTitlesShort() {
        WrongTitleException thrown = Assertions.assertThrows(WrongTitleException.class, () -> {
            taskValidator.validateTitle(SHORT_TITLE);
        }, MIN_TITLE_LENGTH_EXCEPTION_MESSAGE);

        Assertions.assertEquals(MIN_TITLE_LENGTH_EXCEPTION_MESSAGE, thrown.getMessage());
    }


    @Test
    public void ShouldThrowWrongTaskExceptionWhenTaskTitleIsEmpty() {
        EmptyInputException thrown = Assertions.assertThrows(EmptyInputException.class, () -> {
            taskValidator.validateTitle(EMPTY_STRING);
        }, EMPTY_INPUT_EXCEPTION_MESSAGE);

        Assertions.assertEquals(EMPTY_INPUT_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    @Test
    public void ShouldThrowWrongTaskExceptionWhenTaskLinkIsShort() {
        WrongLinkException thrown = Assertions.assertThrows(WrongLinkException.class, () -> {
            taskValidator.validateLink(SHORT_LINK);
        }, MIN_LINK_LENGTH_EXCEPTION_MESSAGE);

        Assertions.assertEquals(MIN_LINK_LENGTH_EXCEPTION_MESSAGE, thrown.getMessage());
    }

    @Test
    public void ShouldThrowWrongTaskExceptionWhenTaskAnswerIsEmpty() {
        EmptyInputException thrown = Assertions.assertThrows(EmptyInputException.class, () -> {
            taskValidator.validateAnswer(EMPTY_STRING);
        }, EMPTY_INPUT_EXCEPTION_MESSAGE);

        Assertions.assertEquals(EMPTY_INPUT_EXCEPTION_MESSAGE, thrown.getMessage());
    }


}