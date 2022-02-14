package com.epam.jwd.onlinetraining.dao.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 * com.epam.jwd.onlinetraining.dao.db public class ThreadLocalTransactionManager
 * extends Object
 * implements TransactionManager
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public class ThreadLocalTransactionManager implements TransactionManager {

    private static final Logger LOGGER = LogManager.getLogger(ThreadLocalTransactionManager.class);

    private static final ThreadLocal<TransactionId> THREAD_CONNECTION = ThreadLocal.withInitial(() -> {
        try {
            return new SimpleTransactionId(new ProxyConnection(ConnectionPool.locking().takeConnection(), ConnectionPool.instance()));
        } catch (InterruptedException e) {
            LOGGER.warn("Thread was interrupted", e);
            Thread.currentThread().interrupt();
            return null;
        }
    });


    public static ThreadLocalTransactionManager getInstance() {
        return ThreadLocalTransactionManager.Holder.INSTANCE;
    }

    private static class Holder {
        private final static ThreadLocalTransactionManager INSTANCE = new ThreadLocalTransactionManager();
    }

    @Override
    public synchronized void initTransaction() {
        final Connection connection = THREAD_CONNECTION.get().getConnection();
        try {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            } //todo: otherwise throw exception
        } catch (SQLException e) {
            LOGGER.error("SQL exc occurred trying to initialize transaction", e);
        }
    }

    @Override
    public synchronized void commitTransaction() {
        final Connection connection = THREAD_CONNECTION.get().getConnection();
        try {
            //should return connection to Connection Pool
            if (!connection.getAutoCommit()) {
                connection.commit();
                connection.setAutoCommit(true);
            }  //todo: otherwise throw exception
            THREAD_CONNECTION.remove();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("SQL exc occurred committing transaction", e);
        }
    }

    @Override
    public synchronized boolean isTransactionActive() {
        try {
            final Connection connection = THREAD_CONNECTION.get().getConnection();
            final boolean transactionActive = !connection.getAutoCommit();
            if (!transactionActive) {
                THREAD_CONNECTION.remove();
                connection.close(); //should return connection to Connection Pool
            }
            return transactionActive;
        } catch (SQLException e) {
            LOGGER.error("SQL exc occurred trying to check transaction status", e);
            return false;
        }
    }

    @Override
    public synchronized Optional<TransactionId> getTransactionId() {
        try {
            final TransactionId transactionId = THREAD_CONNECTION.get();
            if (transactionId.getConnection().getAutoCommit()) {
                THREAD_CONNECTION.remove();
                ((ProxyConnection) transactionId.getConnection()).getConnection().close();
                return Optional.empty();
            }
            return Optional.of(transactionId);
        } catch (SQLException e) {
            LOGGER.error("SQL exc occurred trying to retrieve transaction id", e);
            return Optional.empty();
        }
    }


}
