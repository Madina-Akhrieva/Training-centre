package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.AbstractEntity;
import com.epam.jwd.onlinetraining.dao.model.Account;

public interface AccountDao<T extends AbstractEntity<K>, K> extends Dao<Account, Integer> {
    Boolean delete(T entity);
}