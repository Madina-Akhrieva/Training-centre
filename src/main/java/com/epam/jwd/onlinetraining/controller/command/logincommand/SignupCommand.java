package com.epam.jwd.onlinetraining.controller.command.logincommand;

import com.epam.jwd.onlinetraining.controller.api.RequestFactory;
import com.epam.jwd.onlinetraining.controller.command.common.Command;
import com.epam.jwd.onlinetraining.controller.command.common.CommandRequest;
import com.epam.jwd.onlinetraining.controller.command.common.CommandResponse;
import com.epam.jwd.onlinetraining.controller.command.common.PropertyContext;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.api.AccountService;
import com.epam.jwd.onlinetraining.service.api.ServiceFactory;
import com.epam.jwd.onlinetraining.service.api.UserService;
import com.epam.jwd.onlinetraining.service.exception.AccountWithSuchEmailExists;
import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongFirstNameException;
import com.epam.jwd.onlinetraining.service.exception.WrongLastNameException;
import com.epam.jwd.onlinetraining.service.exception.WrongMailException;
import com.epam.jwd.onlinetraining.service.exception.WrongPasswordException;
import com.epam.jwd.onlinetraining.service.exception.WrongPhoneException;
import com.epam.jwd.onlinetraining.service.exception.WrongPhoneLength;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.controller.command.logincommand public enum SignupCommand
 * extends Enum<SignupCommand>
 * implements Command
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public enum SignupCommand implements Command {
    INSTANCE(ServiceFactory.simple().accountService(), ServiceFactory.simple().userService(), RequestFactory.getInstance(), PropertyContext.instance());

    private static final Logger LOGGER = LogManager.getLogger(SignupCommand.class);
    private static final String LOGIN_JSP_PATH = "page.login";
    private static final String INDEX_JSP_PATH = "page.index";
    private static final String EMAIL_REQUEST_PARAM_NAME = "email";
    private static final String PASSWORD_REQUEST_PARAM_NAME = "password";
    private static final String LASTNAME_REQUEST_PARAM_NAME = "lastname";
    private static final String FIRSTNAME_REQUEST_PARAM_NAME = "firstname";
    private static final String PHONE_REQUEST_PARAM_NAME = "phone";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String ACCOUNT_SESSION_ATTRIBUTE_NAME = "account";
    private static final String ACCOUNT_EXISTS_EXCEPTION_ATTRIBUTE = "accountExistsExceptionMessage";
    private static final String SUCCESSFUL_SIGNUP_ATTRIBUTE = "successfulSignupMessage";
    private static final String SUCCESSFUL_SIGNUP_MESSAGE_TEXT = "Signed up successfully";
    private static final String ERROR_SIGNUP_PASS_ATTRIBUTE = "errorSignPassMessage";
    private static final String WRONG_MAIL_EXCEPTION_ATTRIBUTE = "wrongMailExceptionMessage";
    private static final String WRONG_PHONE_ATTRIBUTE = "wrongPhoneAttribute";
    private static final String WRONG_PASSWORD_ATTRIBUTE = "wrongPasswordMessage";
    private static final String WRONG_PASSWORD_MESSAGE_TEXT = "Unsuitable password";
    private static final String WRONG_FIRSTNAME_ATTRIBUTE = "wrongFirstNameAttribute";
    private static final String WRONG_LASTNAME_ATTRIBUTE = "wrongLastNameAttribute";
    private static final String ACCOUNT_EXISTS_EXCEPTION_MESSAGE_TEXT = "Account with such mail exists ♥";
    private static final String WRONG_MAIL_EXCEPTION_MESSAGE_TEXT = "Your mail is not valid ♥";
    private static final String WRONG_PHONE_MESSAGE_TEXT = "Phone is not suitable ♥";
    private static final String WRONG_LASTNAME_MESSAGE_TEXT = "Lastname is not suitable ♥";
    private static final String WRONG_FIRSTNAME_MESSAGE_TEXT = "Firstname is not suitable ♥";
    private static final String EMPTY_INPUTS_ATTRIBUTE = "emptyInputsMessage";
    private static final Object EMPTY_INPUTS_MESSAGE_TEXT = "None input is allowed to be empty!";

    private final AccountService accountService;
    private final UserService userService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    SignupCommand(AccountService accountService, UserService userService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.userService = userService;
        this.accountService = accountService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String login = request.getParameter(EMAIL_REQUEST_PARAM_NAME);
        final String password = request.getParameter(PASSWORD_REQUEST_PARAM_NAME);
        final String lastname = request.getParameter(LASTNAME_REQUEST_PARAM_NAME);
        final String firstname = request.getParameter(FIRSTNAME_REQUEST_PARAM_NAME);
        final String phone = request.getParameter(PHONE_REQUEST_PARAM_NAME);
        try {
            final Optional<Account> account = accountService.register(login, password);
            final Optional<User> user = userService.register(account.get().getId(), phone, firstname, lastname);
            if (!account.isPresent()) {
                request.addAttributeToJsp(ERROR_SIGNUP_PASS_ATTRIBUTE, LOGIN_JSP_PATH);
                return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
            }
            request.cleareSession();
            request.createSession();
            request.addToSession(ACCOUNT_SESSION_ATTRIBUTE_NAME, account.get());
            request.addAttributeToJsp(USER_ATTRIBUTE_NAME, user);
        } catch (AccountWithSuchEmailExists e) {
            LOGGER.warn("Such account already exists");
            request.addAttributeToJsp(ACCOUNT_EXISTS_EXCEPTION_ATTRIBUTE, ACCOUNT_EXISTS_EXCEPTION_MESSAGE_TEXT);
            request.addToSession(ACCOUNT_EXISTS_EXCEPTION_ATTRIBUTE, ACCOUNT_EXISTS_EXCEPTION_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
        } catch (WrongMailException e) {
            LOGGER.warn("Mail contains unsupported symbols");
            request.addAttributeToJsp(WRONG_MAIL_EXCEPTION_ATTRIBUTE, WRONG_MAIL_EXCEPTION_MESSAGE_TEXT);
            request.addToSession(WRONG_MAIL_EXCEPTION_ATTRIBUTE, WRONG_MAIL_EXCEPTION_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
        } catch (WrongPasswordException e) {
            LOGGER.warn("Password contains unsupported symbols");
            request.addAttributeToJsp(WRONG_PASSWORD_ATTRIBUTE, WRONG_PASSWORD_MESSAGE_TEXT);
            request.addToSession(WRONG_PASSWORD_ATTRIBUTE, WRONG_PASSWORD_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
        } catch (WrongFirstNameException e) {
            LOGGER.warn("Wrong firstname exception");
            request.addAttributeToJsp(WRONG_FIRSTNAME_ATTRIBUTE, WRONG_FIRSTNAME_MESSAGE_TEXT);
            request.addToSession(WRONG_FIRSTNAME_ATTRIBUTE, WRONG_FIRSTNAME_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
        } catch (WrongPhoneException e) {
            LOGGER.warn("Wrong phone exception");
            request.addAttributeToJsp(WRONG_PHONE_ATTRIBUTE, WRONG_PHONE_MESSAGE_TEXT);
            request.addToSession(WRONG_PHONE_ATTRIBUTE, WRONG_PHONE_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
        } catch (WrongLastNameException e) {
            LOGGER.warn("Wrong firstname exception");
            request.addAttributeToJsp(WRONG_LASTNAME_ATTRIBUTE, WRONG_LASTNAME_MESSAGE_TEXT);
            request.addToSession(WRONG_LASTNAME_ATTRIBUTE, WRONG_LASTNAME_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
        } catch (WrongPhoneLength wrongPhoneLength) {
            LOGGER.warn("Wrong phone exception");
            request.addAttributeToJsp(WRONG_PHONE_ATTRIBUTE, WRONG_PHONE_MESSAGE_TEXT);
            request.addToSession(WRONG_PHONE_ATTRIBUTE, WRONG_PHONE_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
        } catch (EmptyInputException e) {
            LOGGER.warn("Empty inputs exception");
            request.addAttributeToJsp(EMPTY_INPUTS_ATTRIBUTE, EMPTY_INPUTS_MESSAGE_TEXT);
            request.addToSession(EMPTY_INPUTS_ATTRIBUTE, EMPTY_INPUTS_MESSAGE_TEXT);
            return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
        } catch (Exception exception) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_JSP_PATH));
        }
        request.addAttributeToJsp(SUCCESSFUL_SIGNUP_ATTRIBUTE, SUCCESSFUL_SIGNUP_MESSAGE_TEXT);
        request.addToSession(SUCCESSFUL_SIGNUP_ATTRIBUTE, SUCCESSFUL_SIGNUP_MESSAGE_TEXT);
        return requestFactory.createRedirectResponse(propertyContext.get(LOGIN_JSP_PATH));
    }
}
