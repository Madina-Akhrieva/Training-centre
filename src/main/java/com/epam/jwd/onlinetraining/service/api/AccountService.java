package com.epam.jwd.onlinetraining.service.api;

import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.service.exception.AccountWithSuchEmailExists;
import com.epam.jwd.onlinetraining.service.exception.WrongMailException;
import com.epam.jwd.onlinetraining.service.exception.WrongPasswordException;

import java.util.Optional;

public interface AccountService extends EntityService<Account> {

    Optional<Account> authenticate(String email, String password);

    Optional<Account> register(String email, String password) throws WrongMailException, WrongPasswordException, AccountWithSuchEmailExists;

    Optional<Account> findByMail(String login);

    Optional<Account> findById(long id);
}
