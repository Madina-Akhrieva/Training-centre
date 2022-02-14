package com.epam.jwd.onlinetraining.dao.db;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * com.epam.jwd.onlinetraining.dao.db @WebListener
 * public class ConnectionPoolInitializeListener
 * extends Object
 * implements ServletContextListener
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
@WebListener
public class ConnectionPoolInitializeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.instance().init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.instance().shutDown();
    }
}
