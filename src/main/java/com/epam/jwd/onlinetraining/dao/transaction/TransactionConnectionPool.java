//package com.epam.jwd.onlinetraining.dao.transaction;
//
//import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPool;
//import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPoolImpl;
//import com.epam.jwd.onlinetraining.dao.connectionpool.ProxyConnection;
//
//import java.sql.Connection;
//import java.util.Optional;
//
//public final class TransactionConnectionPool implements ConnectionPool {
//
//    private final ConnectionPool connectionPool;
//    private final TransactionManager transactionManager;
//
//    public TransactionConnectionPool(ConnectionPool connectionPool, TransactionManager transactionManager) {
//        this.connectionPool = connectionPool;
//        this.transactionManager = transactionManager;
//    }
//
//    @Override
//    public boolean isInitialized() {
//        return connectionPool.isInitialized();
//    }
//
//    @Override
//    public boolean init() {
//        return connectionPool.init();
//    }
//
//    @Override
//    public boolean shutdown() {
//        return connectionPool.shutdown();
//    }
//
//    @Override
//    public Connection requestConnection() {
//        Optional<TransactionId> transactionId = transactionManager.getTransactionId();
//
//        return transactionId.isPresent() ? transactionId.get().getConnection()
//                : new ProxyConnection( this, connectionPool.requestConnection());
//    }
//
//    @Override
//    public void returnConnection(Connection connection) {
//        if (!transactionManager.getTransactionId().isPresent()) {
//            connectionPool.returnConnection(connection);
//        }
//    }
//
//    public static ConnectionPool getInstance() {
//        return Holder.INSTANCE;
//    }
//
//    public static class Holder {
//        public static final ConnectionPool INSTANCE = new TransactionConnectionPool(ConnectionPoolImpl.getInstance(), TransactionManager.instance());
//    }
//}
