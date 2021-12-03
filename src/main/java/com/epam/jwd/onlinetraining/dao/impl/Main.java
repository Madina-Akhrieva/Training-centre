package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.ResultSetExtractor;
import com.epam.jwd.onlinetraining.dao.api.StatementPreparator;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPoolImpl;

import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    private static final ConnectionPool connectionPoll = ConnectionPoolImpl.getInstance();
    private static final String SELECT_ALL_SQL = "select * from course_user";
    private static final String FIND_USERS_OLDER_THAN_SQL = "select * from course_user where age > 30";

    public static void main(String[] args) {
        LOGGER.debug("start program");
        connectionPoll.init();
        final List<User> users = fetchUsersFromDb();
        users.forEach(user -> LOGGER.trace("found user {}", user));
        CourseDaoImpl courseDao = new CourseDaoImpl();
        connectionPoll.shutdown();
        LOGGER.debug("end program");
    }

    private static List<User> fetchUsersFromDb() {
        return executeStatement(SELECT_ALL_SQL, Main::extractUser);
    }

    private static List<User> fetchUsersOlderThan(int i) {
        return executePrepared(
                FIND_USERS_OLDER_THAN_SQL,
                Main::extractUser,
                st -> st.setInt(1, i)
        );
    }

    private static <T extends Entity> List<T> executePrepared(String sql,
                                                                       ResultSetExtractor<T> extractor,
                                                                       StatementPreparator statementPreparation) {
        try (final Connection connection = connectionPoll.requestConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            if (statementPreparation != null) {
                statementPreparation.accept(statement);
            }
            final ResultSet resultSet = statement.executeQuery();
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOGGER.error("sql exception occurred", e);
            LOGGER.debug("sql: {}", sql);
        } catch (EntityExtractionFailedException e) {
            LOGGER.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    private static <T extends Entity> List<T> executeStatement(String sql,
                                                                        ResultSetExtractor<T> extractor) {
        try (final Connection connection = connectionPoll.requestConnection();
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(sql)) {
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOGGER.error("sql exception occurred", e);
            LOGGER.debug("sql: {}", sql);
        } catch (EntityExtractionFailedException e) {
            LOGGER.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }


    private static User extractUser(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new User(
                    resultSet.getString("phone"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("email")
            );
        } catch (SQLException e) {
            LOGGER.error("could not extract value from result set", e);
            throw new EntityExtractionFailedException("failed to extract user");
        }
    }
}
