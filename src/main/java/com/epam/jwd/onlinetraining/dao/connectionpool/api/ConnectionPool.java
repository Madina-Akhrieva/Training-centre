package com.epam.jwd.onlinetraining.dao.connectionpool.api;


import java.sql.Connection;

public interface ConnectionPool {
    boolean init();
    void shutdown();
    Connection requestConnection();
    void returnConnection(Connection connection);

}