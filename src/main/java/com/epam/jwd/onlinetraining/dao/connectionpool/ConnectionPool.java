//package com.epam.jwd.onlinetraining.dao.connectionpool;
//
//
//import com.epam.jwd.onlinetraining.dao.transaction.TransactionConnectionPool;
//
//import java.sql.Connection;
//
//public interface ConnectionPool {
//
//    static ConnectionPool instance() {
//        return TransactionConnectionPool.getInstance();
//    }
//
//    static ConnectionPool locking() {
//        return ConnectionPoolImpl.getInstance();
//    }
//
//    boolean isInitialized();
//
//    boolean init();
//
//    boolean shutdown();
//
//    Connection requestConnection();
//
//    void returnConnection(Connection connection);
//
//}