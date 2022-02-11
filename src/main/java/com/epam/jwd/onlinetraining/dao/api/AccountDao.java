package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.impl.AccountDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Account;

import java.util.Optional;

public interface AccountDao extends EntityDao<Account> {

    Optional<Account> readAccountByEmail(String email);

    static AccountDao instance() {
        return AccountDaoImpl.getInstance();
    }

    Optional<Account> readAccountById(long id);
}
