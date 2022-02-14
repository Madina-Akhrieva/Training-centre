package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongFeedbackException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * com.epam.jwd.onlinetraining.service.validator public class FeedbackValidator
 * extends Object
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class FeedbackValidator {
    private static final String FEEDBACK_CONTAINS_FORBIDDEN_SYMBOLS = "Feedback contains forbidden symbols";
    protected static final String MIN_FEEDBACK_LENGTH_EXCEPTION_MESSAGE = "Feedback length is less than 2";
    protected static final String MAX_FEEDBACK_LENGTH_EXCEPTION_MESSAGE = "Feedback length is more than 500";
    private final static Integer MIN_LENGTH = 2;
    private final static Integer MAX_LENGTH = 500;
    private static final Pattern VALID_FEEDBACK_REGEX =
            Pattern.compile("^[a-zA-Z][0-9a-zA-Z .,'-]*$", Pattern.CASE_INSENSITIVE);
    public static final String EMPTY_STRING = "";
    public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "Feedback Inputs are empty";

    public static FeedbackValidator getInstance() {
        return FeedbackValidator.Holder.INSTANCE;
    }

    public void validateFeedback(String feedback) throws WrongFeedbackException, EmptyInputException {
        if (feedback == EMPTY_STRING) {
            throw new EmptyInputException(EMPTY_INPUT_EXCEPTION_MESSAGE);
        }
        if (feedback.length() < MIN_LENGTH) {
            throw new WrongFeedbackException(MIN_FEEDBACK_LENGTH_EXCEPTION_MESSAGE);
        }
        if (feedback.length() > MAX_LENGTH) {
            throw new WrongFeedbackException(MAX_FEEDBACK_LENGTH_EXCEPTION_MESSAGE);
        }
        if (!matchFeedbackPattern(feedback)) {
            throw new WrongFeedbackException(FEEDBACK_CONTAINS_FORBIDDEN_SYMBOLS);
        }
    }

    public  boolean matchFeedbackPattern(String feedback) {
        Matcher matcher = VALID_FEEDBACK_REGEX.matcher(feedback);
        return matcher.find();
    }

    private static class Holder {
        public static final FeedbackValidator INSTANCE = new FeedbackValidator();
    }
}
