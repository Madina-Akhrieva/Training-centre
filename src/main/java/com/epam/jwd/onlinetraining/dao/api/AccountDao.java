package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.core.AccountDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Account;

import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.dao.api public interface AccountDao
 * extends EntityDao<Account>
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface AccountDao extends EntityDao<Account> {

    Optional<Account> readAccountByEmail(String email);

    static AccountDao instance() {
        return AccountDaoImpl.getInstance();
    }

    Optional<Account> readAccountById(long id);
}
