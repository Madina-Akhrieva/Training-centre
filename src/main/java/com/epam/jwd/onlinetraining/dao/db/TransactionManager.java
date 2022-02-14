package com.epam.jwd.onlinetraining.dao.db;

import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.dao.db public interface TransactionManager
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface TransactionManager {

    void initTransaction();

    void commitTransaction();

    boolean isTransactionActive();

    Optional<TransactionId> getTransactionId();

    static TransactionManager instance() {
        return ThreadLocalTransactionManager.getInstance();
    }

}
