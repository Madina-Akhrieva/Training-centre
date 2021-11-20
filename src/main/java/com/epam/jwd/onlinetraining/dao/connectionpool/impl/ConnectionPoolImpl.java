package com.epam.jwd.onlinetraining.dao.connectionpool.impl;

import com.epam.jwd.onlinetraining.dao.connectionpool.api.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPoolImpl implements com.epam.jwd.onlinetraining.dao.connectionpool.api.ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolImpl.class);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/onlinecourse";
    private static final String USER = "root";
    private static final String PASS = "12345678";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final int CONNECTION_POOL_SIZE = 8;

    private static ConnectionPool INSTANCE;

    private static boolean initialized = false;

    private static final ReentrantLock getInstanceLock = new ReentrantLock();

    private final BlockingDeque<ProxyConnection> availableConnections = new LinkedBlockingDeque<>();
    private final BlockingDeque<ProxyConnection> usedConnections = new LinkedBlockingDeque<>();


    public ConnectionPoolImpl() {
    }

    public static ConnectionPool getInstance() {
        getInstanceLock.lock();
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPoolImpl();
        }
        getInstanceLock.unlock();
        return INSTANCE;
    }

    @Override
    public boolean init() {
        getInstanceLock.lock();
        LOGGER.info("init method start");
        if (!initialized) {
            try {
                initialized = initializeConnections(CONNECTION_POOL_SIZE);
            } catch (ConnectionPoolException e) {
                LOGGER.error("occurred by ConnectionPoolException", e);
            } finally {
                getInstanceLock.unlock();
            }
        }
        LOGGER.info("init method finished");
        return initialized;
    }

    @Override
    public void shutdown() {
        getInstanceLock.lock();
        closeConnections(availableConnections);
        closeConnections(usedConnections);
        deregisterDrivers();
        initialized = false;
        LOGGER.debug("shutdown method finished");
        getInstanceLock.unlock();
    }

    @Override
    public synchronized Connection requestConnection() {
        LOGGER.info("request connection");
        getInstanceLock.lock();
        if (!initialized) {
            init();
        }
        while (availableConnections.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                LOGGER.error("occurred by InterruptedException", e);
            } finally {
                getInstanceLock.unlock();
            }
        }
        ProxyConnection connection = availableConnections.poll();
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public void returnConnection(Connection connection) {
        LOGGER.info("return connection");
        if (usedConnections.remove(connection)) {
            availableConnections.add((ProxyConnection) connection);
            notify();
        }

    }

    private boolean initializeConnections(int amount) throws ConnectionPoolException {

        LOGGER.info("start initializing connections");
        try {
            Class.forName(DRIVER);
            for (int i = 0; i < amount; i++) {
                final Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                final ProxyConnection proxyConnection = new ProxyConnection(this, connection);
                availableConnections.add(proxyConnection);
            }

        } catch (SQLException e) {
            throw new ConnectionPoolException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        LOGGER.info("connection are initialized");
        return true;
    }


    private void closeConnections(Deque<ProxyConnection> connections) {
        LOGGER.info("close connections");
        connections.forEach(this::closeConnection);
    }

    private void closeConnection(ProxyConnection connection) {
        try {
            connection.realClose();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        LOGGER.info("close connection");
    }

    private static void deregisterDrivers() {
        LOGGER.trace("unregistering sql drivers");
        final Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOGGER.error("could not deregister driver", e);
            }
        }
    }
}
