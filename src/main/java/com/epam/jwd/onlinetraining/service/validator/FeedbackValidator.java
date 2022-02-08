package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.WrongFeedbackException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeedbackValidator {
    public static final String TITLE_CONTAINS_FORBIDDEN_CHARACTERS_MESSAGE = "Title contains forbidden characters";
    private static final String FEEDBACK_CONTAINS_FORBIDDEN_SYMBOLS = "Feedback contains forbidden symbols";
    private static final String MIN_FEEDBACK_LENGTH_EXCEPTION_MESSAGE = "Feedback length is less than 2";
    private static final String MAX_FEEDBACK_LENGTH_EXCEPTION_MESSAGE = "Feedback length is more than 500";
    private final static Integer MIN_LENGTH = 2;
    private final static Integer MAX_LENGTH = 500;
    private static final Pattern VALID_FEEDBACK_REGEX =
            Pattern.compile("^[a-zA-Z][0-9a-zA-Z .,'-]*$", Pattern.CASE_INSENSITIVE);


    public void validateFeedback(String feedback) throws WrongFeedbackException {
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

    public static boolean matchFeedbackPattern(String feedback) {
        Matcher matcher = VALID_FEEDBACK_REGEX.matcher(feedback);
        return matcher.find();
    }

    public static FeedbackValidator getInstance() {
        return FeedbackValidator.Holder.INSTANCE;
    }


    private static class Holder {
        public static final FeedbackValidator INSTANCE = new FeedbackValidator();
    }
}
