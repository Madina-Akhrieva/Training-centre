package com.epam.jwd.onlinetraining.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.epam.jwd.onlinetraining.dao.api.AccountDao;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.Role;
import com.epam.jwd.onlinetraining.service.api.AccountService;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

public class SimpleAccountService implements AccountService {

    public static final byte[] DUMMY_PASSWORD = "password".getBytes(StandardCharsets.UTF_8);
    private final AccountDao accountDao;
    private final BCrypt.Hasher hasher;
    private final BCrypt.Verifyer verifyer;

    public SimpleAccountService(AccountDao accountDao, BCrypt.Hasher hasher, BCrypt.Verifyer verifyer) {
        this.accountDao = accountDao;
        this.hasher = hasher;
        this.verifyer = verifyer;
    }


//    @Override
//    public Optional<Account> authenticate(String email, String password) {
//        if (email == null || password == null) {
//            return Optional.empty();
//        }
//        byte[] enteredPassword = password.getBytes(StandardCharsets.UTF_8);
//        final Optional<Account> readAccount = accountDao.readAccountByEmail(email);
//        if (readAccount.isPresent()) {
//            final byte[] hashedPassword = readAccount.get()
//                    .getPassword()
//                    .getBytes(StandardCharsets.UTF_8);
//            return verifyer.verify(enteredPassword, hashedPassword).verified
//                    ? readAccount
//                    : Optional.empty();
//        } else {
//            protectFromTimingAttack(enteredPassword);
//            return Optional.empty();
//        }
//
//    }

    @Override
    public Optional<Account> register(String email, String password) {
        if (email == null || password == null) {
            return Optional.empty();
        }
        final Optional<Account> readAccount = accountDao.readAccountByEmail(email);
        if (readAccount.isPresent()) {
            return Optional.empty();
        } else {
            final char[] rawPassword = password.toCharArray();
            final String hashedPassword = hasher.hashToString(MIN_COST, rawPassword);
            Account account = accountDao.create(new Account(null, hashedPassword, email));
            return Optional.ofNullable(account);
        }

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
            return verifyer.verify(enteredPassword, hashedPassword).verified
                    ? readAccount
                    : Optional.empty();
        } else {
            protectFromTimingAttack(enteredPassword);
            return Optional.empty();
        }
    }

    private void protectFromTimingAttack(byte[] enteredPassword) {
        verifyer.verify(enteredPassword, DUMMY_PASSWORD);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.read();
    }

    @Override
    public Optional<Account> create(Account entity) {
        return Optional.empty();
    }

//    @Override
//    public Optional<Account> create(Account account) {
//        final char[] rawPassword = account.getPassword().toCharArray();
//        final String hashedPassword = hasher.hashToString(MIN_COST, rawPassword);
//        Optional.ofNullable(accountDao.create(account.withPassword(hashedPassword)));
//        return Optional.empty();
//    }

    @Override
    public Account add(Account account) {
        return accountDao.create(account);
    }
}

