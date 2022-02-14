package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.service.exception.AccountWithSuchEmailExists;
import com.epam.jwd.onlinetraining.service.exception.EmptyInputException;
import com.epam.jwd.onlinetraining.service.exception.WrongMailException;
import com.epam.jwd.onlinetraining.service.exception.WrongPasswordException;

import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.service.api public interface AccountService
 * extends EntityService<Account>
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface AccountService extends EntityService<Account> {

    Optional<Account> authenticate(String email, String password) throws EmptyInputException, Exception;

    Optional<Account> register(String email, String password) throws WrongMailException, WrongPasswordException, AccountWithSuchEmailExists, EmptyInputException;

    Optional<Account> findById(long id);
}
