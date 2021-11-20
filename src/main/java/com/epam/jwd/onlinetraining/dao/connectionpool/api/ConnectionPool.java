package com.epam.jwd.onlinetraining.dao.connectionpool.api;


import com.epam.jwd.onlinetraining.dao.connectionpool.exception.CouldNotInitializeConnectionPool;

import java.sql.Connection;

public interface ConnectionPool {
    boolean init() throws CouldNotInitializeConnectionPool;

    void shutdown();

    Connection requestConnection();

    void returnConnection(Connection connection);

}