package com.epam.jwd.onlinetraining.dao.connectionpool.impl;

import com.epam.jwd.onlinetraining.dao.connectionpool.api.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public final class ConnectionPoolImpl implements com.epam.jwd.onlinetraining.dao.connectionpool.api.ConnectionPool {

    private static ConnectionPool INSTANCE;
    private static boolean initialized = false;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/onlinecourse";
    private static final String USER = "root";
    private static final String PASS = "12345678";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int CONNECTION_POOL_SIZE = 5;

    private final Queue<ProxyConnection> availableConnections = new ArrayBlockingQueue<>(CONNECTION_POOL_SIZE);
    private final List<ProxyConnection> usedConnections = new CopyOnWriteArrayList<>();


    public ConnectionPoolImpl() {
    }

    public static synchronized com.epam.jwd.onlinetraining.dao.connectionpool.api.ConnectionPool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPoolImpl();
        }
        return INSTANCE;
    }

    @Override
    public boolean init() {
        if (!initialized) {
            try {
                initialized = initializeConnections(CONNECTION_POOL_SIZE);
            } catch (ConnectionPoolException e) {
                //todo:add log
            }
        }
        return initialized;
    }

    @Override
    public void shutdown() {
        closeConnections(availableConnections);
        closeConnections(usedConnections);
    }

    @Override
    public synchronized Connection requestConnection() {
        if (!initialized) {
            init();
        }
        while (availableConnections.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                //todo:add log
            }
        }

        ProxyConnection connection = availableConnections.poll();
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public synchronized void returnConnection(Connection connection) {
        if (usedConnections.remove(connection)) {
            availableConnections.add((ProxyConnection) connection);
            this.notifyAll();
        }
    }

    private boolean initializeConnections(int amount) throws ConnectionPoolException {

        try {
            for (int i = 0; i < amount; i++) {
                Class.forName(DRIVER);
                final Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                final ProxyConnection proxyConnection = new ProxyConnection(this, connection);
                availableConnections.add(proxyConnection);

            }
            //todo:delete comment
            System.out.println(" 5 connections have been initialized");
        } catch (SQLException e) {
            throw new ConnectionPoolException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }


    private void closeConnections(Collection<ProxyConnection> connections) {
        for (ProxyConnection connection : connections) {
            closeConnection(connection);
        }
        //todo:delete comment
        System.out.println("Connections have been closed successfully");
    }

    private void closeConnection(ProxyConnection connection) {
        try {
            connection.realClose();
        } catch (SQLException exception) {
            //todo:add log
            exception.printStackTrace();
        }
    }
}
