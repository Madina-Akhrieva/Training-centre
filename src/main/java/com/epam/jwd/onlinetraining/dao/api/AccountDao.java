package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.dao.model.Account;

public interface AccountDao<T extends Entity<K>, K> extends Dao<Account, Integer> {
    Boolean delete(T entity);
}
