package com.epam.jwd.onlinetraining.dao.connectionpool;


import java.sql.Connection;

public interface ConnectionPool {
    boolean init();

    boolean shutdown();

    //todo : add interrupted exception
    Connection requestConnection();

    void returnConnection(Connection connection);

}