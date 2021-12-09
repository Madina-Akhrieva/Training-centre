package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.api.AccountDao;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.service.api.AccountService;

import java.util.List;
import java.util.Optional;

public class SimpleAccountService implements AccountService {

    private final AccountDao accountDao;

    public SimpleAccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
    public Optional<Account> authenticate(String email, String password) {
        final Optional<Account> readAccount = accountDao.readAccountByEmail(email);
        return readAccount.filter(account -> account.getPassword().equals(password));
    }

    @Override
    public List<Account> findAll() {
        return accountDao.read();
    }
}
