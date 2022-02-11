package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.WrongFirstNameException;
import com.epam.jwd.onlinetraining.service.exception.WrongLastNameException;
import com.epam.jwd.onlinetraining.service.exception.WrongPhoneException;
import com.epam.jwd.onlinetraining.service.exception.WrongPhoneLength;

public class UserValidator {

    private static final String WRONG_PHONE_LENGTH_MESSAGE = "Phone length is 13. Check please ♥";
    private static final String WRONG_PHONE_EXCEPTION_MESSAGE = "Phone is wrong";
    private static final String PHONE_PATTERN = "[\\+]375\\d{2}\\d{3}\\d{2}\\d{2}";
    private static final String FIRSTNAME_PATTERN = "^[a-zA-Z][0-9a-zA-Z .,'-]*$";
    private static final String LASTNAME_PATTERN = "^[a-zA-Z][0-9a-zA-Z .,'-]*$";
    private static final String WRONG_FIRSTNAME_EXCEPTION_MESSAGE = "Name exception is thrown";
    private static final String WRONG_LASTNAME_EXCEPTION_MESSAGE = "Lastname exception is thrown";
    private static final int PHONE_LENGTH = 13;

    public void validatePhone(String phoneNumber) throws WrongPhoneException, WrongPhoneLength {
        if(phoneNumber.length()!= PHONE_LENGTH){
            throw new WrongPhoneLength(WRONG_PHONE_LENGTH_MESSAGE);
        }
        if (!phoneNumber.matches(PHONE_PATTERN)) {
            throw new WrongPhoneException(WRONG_PHONE_EXCEPTION_MESSAGE);
        }

    }

    public void validateFirstname(String firstName) throws WrongFirstNameException {
        if (!firstName.matches(FIRSTNAME_PATTERN)) {
            throw new WrongFirstNameException(WRONG_FIRSTNAME_EXCEPTION_MESSAGE);
        }
    }

    public void validateLastname(String lastName) throws WrongLastNameException {
        if (!lastName.matches(LASTNAME_PATTERN)) {
            throw new WrongLastNameException(WRONG_LASTNAME_EXCEPTION_MESSAGE);
        }
    }


    public static UserValidator getInstance() {
        return UserValidator.Holder.INSTANCE;
    }



    private static class Holder {
        public static final UserValidator INSTANCE = new UserValidator();
    }
}
