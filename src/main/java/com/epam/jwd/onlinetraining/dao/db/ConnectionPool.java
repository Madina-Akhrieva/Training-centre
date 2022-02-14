package com.epam.jwd.onlinetraining.dao.db;

import java.sql.Connection;

/**
 * com.epam.jwd.onlinetraining.dao.db public interface ConnectionPool
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface ConnectionPool {

    static ConnectionPool instance() {
        return TransactionConnectionPool.getInstance();
    }

    static ConnectionPool locking() {
        return LockingConnectionPool.getInstance();
    }

    boolean isInitialized();

    boolean init() throws CouldNotInitializeConnectionPoolError;

    boolean shutDown();

    Connection takeConnection() throws InterruptedException;

    void returnConnection(Connection connection);
}
