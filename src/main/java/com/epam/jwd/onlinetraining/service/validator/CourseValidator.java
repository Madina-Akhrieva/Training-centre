package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongDescriptionException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.epam.jwd.onlinetraining.service.validator public class CourseValidator
 * extends Object
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class CourseValidator {
    public static final String TITLE_CONTAINS_FORBIDDEN_CHARACTERS_MESSAGE = "Title contains forbidden characters";
    private static final String DESCRIPTION_CONTAINS_FORBIDDEN_SYMBOLS = "Description contains forbidden symbols";
    private static final String MIN_TITLE_LENGTH_EXCEPTION_MESSAGE = "Title length is less than 2";
    private static final String MAX_TITLE_LENGTH_EXCEPTION_MESSAGE = "Title length is more than 70";
    private static final String MIN_DESCRIPTION_LENGTH_EXCEPTION_MESSAGE = "Description length is less than 2";
    private static final String MAX_DESCRIPTION_LENGTH_EXCEPTION_MESSAGE = "Description length is more than 70";
    private final static Integer MIN_LENGTH = 2;
    private final static Integer MAX_LENGTH = 50;
    public static final Pattern VALID_TITLE_REGEX =
            Pattern.compile("^[a-zA-Z][0-9a-zA-Z .,'-]*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_DESCRIPTION_REGEX =
            Pattern.compile("^[a-zA-Z][0-9a-zA-Z .,'-]*$", Pattern.CASE_INSENSITIVE);
    public static final String EMPTY_STRING = "";
    public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "Title Inputs are empty";

    public static CourseValidator getInstance() {
        return CourseValidator.Holder.INSTANCE;
    }

    public void validateTitle(String title) throws WrongTitleException, EmptyInputException {
        if (title == EMPTY_STRING) {
            throw new EmptyInputException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
        if (title.length() < MIN_LENGTH) {
            throw new WrongTitleException(MIN_TITLE_LENGTH_EXCEPTION_MESSAGE);
        }
        if (title.length() > MAX_LENGTH) {
            throw new WrongTitleException(MAX_TITLE_LENGTH_EXCEPTION_MESSAGE);
        }
        if (!matchTitlePattern(title)) {
            throw new WrongTitleException(TITLE_CONTAINS_FORBIDDEN_CHARACTERS_MESSAGE);
        }
    }

    public boolean matchTitlePattern(String title) {
        Matcher matcher = VALID_TITLE_REGEX.matcher(title);
        return matcher.find();
    }


    public void validateDescription(String description) throws WrongDescriptionException, EmptyInputException {
        if (description == "") {
            throw new EmptyInputException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
        if (description.length() < MIN_LENGTH) {
            throw new WrongDescriptionException(MIN_DESCRIPTION_LENGTH_EXCEPTION_MESSAGE);
        }
        if (description.length() > MAX_LENGTH) {
            throw new WrongDescriptionException(MAX_DESCRIPTION_LENGTH_EXCEPTION_MESSAGE);
        }
        if (!matchDescriptionPattern(description)) {
            throw new WrongDescriptionException(DESCRIPTION_CONTAINS_FORBIDDEN_SYMBOLS);
        }
    }

    public boolean matchDescriptionPattern(String description) {
        Matcher matcher = VALID_DESCRIPTION_REGEX.matcher(description);
        return matcher.find();
    }

    private static class Holder {
        public static final CourseValidator INSTANCE = new CourseValidator();
    }


}
