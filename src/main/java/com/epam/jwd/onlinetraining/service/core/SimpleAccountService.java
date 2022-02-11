package com.epam.jwd.onlinetraining.service.core;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.epam.jwd.onlinetraining.dao.api.AccountDao;
import com.epam.jwd.onlinetraining.dao.api.UserDao;
import com.epam.jwd.onlinetraining.dao.core.AccountDaoImpl;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.service.api.AccountService;
import com.epam.jwd.onlinetraining.service.exception.AccountWithSuchEmailExists;
import com.epam.jwd.onlinetraining.service.exception.WrongMailException;
import com.epam.jwd.onlinetraining.service.exception.WrongPasswordException;
import com.epam.jwd.onlinetraining.service.validator.AccountValidator;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

public class SimpleAccountService implements AccountService {

    public static final byte[] DUMMY_PASSWORD = "password".getBytes(StandardCharsets.UTF_8);
    private final AccountValidator accountValidator;
    private final AccountDao accountDao;
    private final UserDao userDao;
    private final BCrypt.Hasher hasher;
    private final BCrypt.Verifyer verifier;

    public SimpleAccountService(AccountValidator accountValidator, AccountDao accountDao, UserDao userDao, BCrypt.Hasher hasher, BCrypt.Verifyer verifier) {
        this.accountValidator = accountValidator;
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.hasher = hasher;
        this.verifier = verifier;
    }


    @Override
    public Optional<Account> register(String email, String password) throws WrongMailException, WrongPasswordException, AccountWithSuchEmailExists {
        if (email == null || password == null) {
            return Optional.empty();
        }
        accountValidator.validateMail(email);
        accountValidator.validatePassword(password);
        final Optional<Account> readAccount = accountDao.readAccountByEmail(email);
        if (readAccount.isPresent()) {
            throw new AccountWithSuchEmailExists("Such account already exists exception");
        } else {
            final char[] rawPassword = password.toCharArray();
            final String hashedPassword = hasher.hashToString(MIN_COST, rawPassword);
            Account account = accountDao.create(new Account(hashedPassword, email));
            return Optional.ofNullable(account);
        }

    }

    @Override
    public Optional<Account> findByMail(String mail) {
        return accountDao.readAccountByEmail(mail);
    }

    @Override
    public Optional<Account> findById(long id) {
        return accountDao.readAccountById(id);
    }

    @Override
    public Optional<Account> authenticate(String mail, String password) {

        if (mail == null || password == null) {
            return Optional.empty();
        }
        final byte[] enteredPassword = password.getBytes(StandardCharsets.UTF_8);
        final Optional<Account> readAccount = accountDao.readAccountByEmail(mail);
        if (readAccount.isPresent()) {
            final byte[] hashedPassword = readAccount.get()
                    .getPassword()
                    .getBytes(StandardCharsets.UTF_8);
            return verifier.verify(enteredPassword, hashedPassword).verified
                    ? readAccount
                    : Optional.empty();
        } else {
            protectFromTimingAttack(enteredPassword);
            return Optional.empty();
        }
    }

    private void protectFromTimingAttack(byte[] enteredPassword) {
        verifier.verify(enteredPassword, DUMMY_PASSWORD);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.read();
    }

    @Override
    public List<Account> findAll(long id) {
        return null;
    }

    @Override
    public Account create(Account entity) {
        return null;
    }


    @Override
    public Account add(Account account) {
        return accountDao.create(account);
    }

}

