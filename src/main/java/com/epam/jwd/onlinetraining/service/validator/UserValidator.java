package com.epam.jwd.onlinetraining.service.validator;

import com.epam.jwd.onlinetraining.service.exception.WrongFirstNameException;
import com.epam.jwd.onlinetraining.service.exception.WrongLastNameException;
import com.epam.jwd.onlinetraining.service.exception.WrongPhoneException;

public class UserValidator {

    private static final String WRONG_PHONE_EXCEPTION_MESSAGE = "Phone is wrong";
    /*
    *   re.test('(212) 348-2626'); // true
        re.test('+1 832-393-1000'); // true
        re.test('+1 202-456-11-11'); // false
    * */
    public static final String PHONE_PATTERN = "[\\+]375\\d{2}\\d{3}\\d{2}\\d{2}";
    public static final String FIRSTNAME_PATTERN = "^[a-zA-Z][0-9a-zA-Z .,'-]*$";
    public static final String LASTNAME_PATTERN = "^[a-zA-Z][0-9a-zA-Z .,'-]*$";
    private static final String WRONG_FIRSTNAME_EXCEPTION_MESSAGE = "Name exception is thrown";
    private static final String WRONG_LASTNAME_EXCEPTION_MESSAGE = "Lastname exception is thrown";

    public void validatePhone(String phoneNumber) throws WrongPhoneException {
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

    public static boolean checkTelNumber(String telNumber)
    {           //если номер содержит + в начале, откр.скобочка и - которые встречается один раз (?), встречаются 11 раз
        // цифры и - и скобочки по одному разу и в конце 12-я цифра
        return (telNumber.matches("^\\+[\\(\\-]?(\\d[\\(\\)\\-]?){11}\\d$") ||
                //ИЛИ один раз откр.скобочка, 9 раз цифры, 1 раз скобочки и - скобочки по одному разу и в конце 10-я цифра
                telNumber.matches("^\\(?(\\d[\\-\\(\\)]?){9}\\d$")) &&
                //И + один раз сколько угодно цифр, но если скобочка открыта в ней могут быть только 3 цифры, (ddd) 1 раз
                //сколько угодно цифр до знака - потом опять так потом сколько угодно цифр до последней цифры
                telNumber.matches("[\\+]?\\d*(\\(\\d{3}\\))?\\d*\\-?\\d*\\-?\\d*\\d$");
    }

    public static UserValidator getInstance() {
        return UserValidator.Holder.INSTANCE;
    }



    private static class Holder {
        public static final UserValidator INSTANCE = new UserValidator();
    }
}
