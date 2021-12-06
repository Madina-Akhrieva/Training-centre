//package com.epam.jwd.onlinetraining.dao.transaction;
//
//import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPool;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Optional;
//
//public enum ThreadLocalTransactionManager implements TransactionManager {
//
//    INSTANCE;
//
//    private static final Logger LOGGER = LogManager.getLogger(ThreadLocalTransactionManager.class);
//
//    public static final ThreadLocal<TransactionId> THREAD_CONNECTION = ThreadLocal
//            .withInitial(() -> new SimpleTransactionId(ConnectionPool.locking().requestConnection()));
//
//
//    @Override
//    public synchronized void initTransaction() {
//        final Connection connection = THREAD_CONNECTION.get().getConnection();
//        try {
//            if (connection.getAutoCommit()) {
//                connection.setAutoCommit(false);
//            }//todo:throw exception
//        } catch (SQLException exception) {
//            LOGGER.error("occurred by SQLException  trying to initialize transaction", exception);
//            exception.printStackTrace();
//        }
//    }
//
//    @Override
//    public synchronized void commitTransaction() {
//        final Connection connection = THREAD_CONNECTION.get().getConnection();
//        try {
//            if (connection.getAutoCommit()) {
//                connection.commit();
//                connection.setAutoCommit(true);
//
//            }
//            THREAD_CONNECTION.remove();
//            connection.close();
//        } catch (SQLException exception) {
//            LOGGER.error("occurred by SQLException  trying to commit transaction", exception);
//        }
//    }
//
//    @Override
//    public synchronized boolean isTransactionActive() {
//        try {
//            Connection connection = THREAD_CONNECTION.get().getConnection();
//            final boolean transactionActive = connection.getAutoCommit();
//            if (!transactionActive) {
//                THREAD_CONNECTION.remove();
//                connection.close();//should return connection to connection pool
//
//            }
//            return transactionActive;
//        } catch (SQLException exception) {
//            LOGGER.error("occurred by SQLException  trying to check transaction status ", exception);
//            return false;
//        }
//    }
//
//    @Override
//    public synchronized Optional<TransactionId> getTransactionId() {
//        try{
//
//            final TransactionId transactionId = THREAD_CONNECTION.get();
//            if (transactionId.getConnection().getAutoCommit()) {
//                THREAD_CONNECTION.remove();
//                transactionId.getConnection().close();
//                return Optional.empty();
//            }
//            Optional.of(transactionId);
//        } catch (SQLException exception) {
//            LOGGER.error("occurred by SQLException  trying to retrieve transaction id ", exception);
//            return Optional.empty();
//        }
//        return null;
//    }
//}
