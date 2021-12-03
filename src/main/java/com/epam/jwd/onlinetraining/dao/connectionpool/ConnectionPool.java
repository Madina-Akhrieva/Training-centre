package com.epam.jwd.onlinetraining.dao.connectionpool;


import java.sql.Connection;

public interface ConnectionPool {

    boolean init();

    boolean shutdown();

    Connection requestConnection();

    void returnConnection(Connection connection);

}