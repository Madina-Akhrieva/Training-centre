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
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPoolImpl implements com.epam.jwd.onlinetraining.dao.connectionpool.api.ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolImpl.class);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/onlinecourse";
    private static final String USER = "root";
    private static final String PASS = "12345678";
    private static final int CONNECTION_POOL_SIZE = 8;

    private static ConnectionPool INSTANCE;

    private static boolean initialized = false;

    private static final ReentrantLock getInstanceLock = new ReentrantLock();
    Condition condition = getInstanceLock.newCondition();

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
        if (!initialized) {
            try {
                registerDrivers();
                initializeConnections(CONNECTION_POOL_SIZE);
                initialized = true;
            } catch (ConnectionPoolException e) {
                LOGGER.error("occurred by ConnectionPoolException", e);
            } finally {
                getInstanceLock.unlock();
            }
        }
        return initialized;
    }


    private void initializeConnections(int amount) throws ConnectionPoolException {
        try {

            for (int i = 0; i < amount; i++) {
                final Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
                final ProxyConnection proxyConnection = new ProxyConnection(this, connection);
                LOGGER.info("connection is initialized {}", connection);
                availableConnections.add(proxyConnection);
            }

        } catch (SQLException e) {
            throw new ConnectionPoolException();
        }
        LOGGER.debug("connections are initialized");

    }

    private void registerDrivers() {
        try {
            DriverManager.registerDriver(DriverManager.getDriver(DB_URL));
            LOGGER.trace("drivers registered successfully");
        } catch (SQLException e) {
            LOGGER.error("couldn't register drivers", e);
        }
    }

    //todo : add throw interrupted exception
    @Override
    public  Connection requestConnection() {
        getInstanceLock.lock();
        if (!initialized) {
            init();
        }
        while (availableConnections.isEmpty()) {
            try {
                condition.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error("occurred by InterruptedException", e);
            } finally {
                getInstanceLock.unlock();
            }
        }
        ProxyConnection connection = availableConnections.poll();
        LOGGER.info("request connection {}",connection);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public void returnConnection(Connection connection) {

        getInstanceLock.lock();
        if (usedConnections.remove(connection)) {
            availableConnections.add((ProxyConnection) connection);
            condition.signalAll();
//            this.notifyAll();
        }else{
            LOGGER.warn("attempt to return unknown connection to connection pool. Connection: {}",connection);
        }
        LOGGER.info("return connection {}", connection);
        getInstanceLock.unlock();
    }

    @Override
    public boolean shutdown() {
        getInstanceLock.lock();
        if(initialized) {
            closeConnections();
            deregisterDrivers();
            initialized = false;
            return true;
        }
        getInstanceLock.unlock();
        return false;
    }

    private void closeConnections() {
        closeConnections(availableConnections);
        closeConnections(usedConnections);
        LOGGER.debug("connections are closed");

    }


    private void closeConnections(Deque<ProxyConnection> connections) {
        connections.forEach(this::closeConnection);

    }

    private void closeConnection(ProxyConnection connection) {
        try {
            connection.realClose();
            LOGGER.info("closed connection {}",connection);
        } catch (SQLException exception) {
            LOGGER.error("could not close connections", exception);
        }
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
