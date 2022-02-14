package com.epam.jwd.onlinetraining.dao.db;

import java.sql.Connection;
import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.dao.db public final class TransactionConnectionPool
 * extends Object
 * implements ConnectionPool
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public final class TransactionConnectionPool implements ConnectionPool {

    private final ConnectionPool connectionPool;
    private final TransactionManager transactionManager;

    private TransactionConnectionPool(ConnectionPool connectionPool, TransactionManager transactionManager) {
        this.connectionPool = connectionPool;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean isInitialized() {
        return connectionPool.isInitialized();
    }

    @Override
    public boolean init() throws CouldNotInitializeConnectionPoolError {
        return connectionPool.init();
    }

    @Override
    public boolean shutDown() {
        return connectionPool.shutDown();
    }

    @Override
    public Connection takeConnection() throws InterruptedException {
        final Optional<TransactionId> transactionId = transactionManager.getTransactionId();
        return transactionId.isPresent()
                ? transactionId.get().getConnection()
                : new ProxyConnection(connectionPool.takeConnection(), this);
    }

    @Override
    public void returnConnection(Connection connection) {
        if (!transactionManager.getTransactionId().isPresent()) {
            connectionPool.returnConnection(((ProxyConnection) connection).getConnection());
        }
    }

    public static ConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ConnectionPool INSTANCE = new TransactionConnectionPool(LockingConnectionPool.getInstance(), TransactionManager.instance());
    }
}
