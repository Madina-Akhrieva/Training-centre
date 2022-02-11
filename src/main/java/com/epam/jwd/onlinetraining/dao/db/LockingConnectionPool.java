package com.epam.jwd.onlinetraining.dao.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class LockingConnectionPool implements ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(LockingConnectionPool.class);
    private static final int INITIAL_CONNECTIONS_AMOUNT = 8;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/onlinecourse";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345678";

    private final ReentrantLock lock = new ReentrantLock();
    private final Queue<ProxyConnection> availableConnections = new ConcurrentLinkedDeque<>();
    private final List<ProxyConnection> givenAwayConnections = new CopyOnWriteArrayList<>();

    private boolean initialized = false;


    public static LockingConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private final static LockingConnectionPool INSTANCE = new LockingConnectionPool();
    }

    @Override
    public  boolean isInitialized() {
        return initialized;
    }

    @Override
    public  boolean init() {
        lock.lock();
        try {
            if (!initialized) {
                registerDrivers();
                initializeConnections(INITIAL_CONNECTIONS_AMOUNT, true);
                initialized = true;
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }


    @Override
    public  boolean shutDown() {
        lock.lock();
        try {
            if (initialized) {
                closeConnections();
                deregisterDrivers();
                initialized = false;
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    @Override
    public  Connection takeConnection() throws InterruptedException {
        final ProxyConnection connection;
        lock.lock();
        try {
            while (availableConnections.isEmpty()) {
                this.wait();
            }
            connection = availableConnections.poll();
            givenAwayConnections.add(connection);
        } finally {
            lock.unlock();
        }
        return connection;
    }

    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public  void returnConnection(Connection connection) {
        lock.lock();
        try {
            if (givenAwayConnections.remove(connection)) {
                availableConnections.add((ProxyConnection) connection);
            } else {
                LOGGER.warn("Attempt to add unknown connection to Connection Pool. Connection: {}", connection);
            }
        } finally {
            lock.unlock();
        }
    }

    private void registerDrivers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            LOGGER.trace("drivers registered successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.error("couldn't register drivers", e);
        }
    }
    private void initializeConnections(int amount, boolean failOnConnectionException) {
        try {
            for (int i = 0; i < amount; i++) {
                final Connection conn = DriverManager
                        .getConnection(DB_URL, DB_USER, DB_PASSWORD);
                LOGGER.info("initialized connection {}", conn);
                final ProxyConnection proxyConnection = new ProxyConnection(conn, this);
                availableConnections.add(proxyConnection);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occurred creating Connection", e);
            if (failOnConnectionException) {
                throw new CouldNotInitializeConnectionPoolError("Failed to create Connection", e);
            }
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

    private void closeConnections() {
        closeConnections(this.availableConnections);
        closeConnections(this.givenAwayConnections);
    }

    private void closeConnections(Collection<ProxyConnection> connections) {
        for (ProxyConnection conn : connections) {
            closeConnection(conn);
        }
    }

    private void closeConnection(ProxyConnection conn) {
        try {
            conn.realClose();
            LOGGER.info("closed connection {}", conn);
        } catch (SQLException e) {
            LOGGER.error("Could not close connection", e);
        }
    }

}
