package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongDescriptionException;
import com.epam.jwd.onlinetraining.service.exception.WrongLinkException;
import com.epam.jwd.onlinetraining.service.exception.WrongTitleException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.epam.jwd.onlinetraining.service.validator public class TaskValidator
 * extends Object
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class TaskValidator {
    public static final String TITLE_CONTAINS_FORBIDDEN_CHARACTERS_MESSAGE = "Title contains forbidden characters";
    private static final String LINK_CONTAINS_FORBIDDEN_SYMBOLS = "Description contains forbidden symbols";
    private static final String MIN_TITLE_LENGTH_EXCEPTION_MESSAGE = "Title length is less than 2";
    private static final String MAX_TITLE_LENGTH_EXCEPTION_MESSAGE = "Title length is more than 70";
    private static final String MIN_LINK_LENGTH_EXCEPTION_MESSAGE = "Link length is less than 2";
    private static final String MAX_LINK_LENGTH_EXCEPTION_MESSAGE = "Link length is more than 70";
    private final static Integer MIN_LENGTH = 2;
    private final static Integer MAX_LENGTH = 50;
    public static final Pattern VALID_TITLE_REGEX =
            Pattern.compile("^[a-zA-Z][0-9a-zA-Z .,'-]*$", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_LINK_REGEX =
            Pattern.compile("(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})", Pattern.CASE_INSENSITIVE);
    public static final String EMPTY_STRING = "";
    public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "EmptyInputException is caught";

    public static TaskValidator getInstance() {
        return TaskValidator.Holder.INSTANCE;
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


    public void validateLink(String link) throws WrongLinkException, EmptyInputException {
        if (link == EMPTY_STRING) {
            throw new EmptyInputException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
        if (link.length() < MIN_LENGTH) {
            throw new WrongLinkException(MIN_LINK_LENGTH_EXCEPTION_MESSAGE);
        }
        if (!matchLinkPattern(link)) {
            throw new WrongLinkException(LINK_CONTAINS_FORBIDDEN_SYMBOLS);
        }

    }

    public boolean matchLinkPattern(String link) {
        Matcher matcher = VALID_LINK_REGEX.matcher(link);
        return matcher.find();
    }

    public void validateAnswer(String answer) throws WrongLinkException, EmptyInputException {
        if (answer == EMPTY_STRING) {
            throw new EmptyInputException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
        if (answer.length() < MIN_LENGTH) {
            throw new WrongLinkException(MIN_LINK_LENGTH_EXCEPTION_MESSAGE);
        }
        if (!matchLinkPattern(answer)) {
            throw new WrongLinkException(LINK_CONTAINS_FORBIDDEN_SYMBOLS);
        }
    }

    private static class Holder {
        public static final TaskValidator INSTANCE = new TaskValidator();
    }

}
