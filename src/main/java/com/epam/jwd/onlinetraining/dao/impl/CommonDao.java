package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.EntityDao;
import com.epam.jwd.onlinetraining.dao.api.ResultSetExtractor;
import com.epam.jwd.onlinetraining.dao.api.StatementPreparator;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static java.lang.String.format;
import static java.lang.String.join;

public abstract class CommonDao<T extends Entity> implements EntityDao<T> {

    private static final Logger LOGGER = LogManager.getLogger(CommonDao.class);

    protected static final String SPACE = " ";


    protected static final String SELECT_ALL_FROM = "select %s from ";
    protected static final String WHERE_FIELD = "where %s = ?";

    protected final ConnectionPool pool;
    private final String selectAllExpression;
    private final String selectByIdExpression;

    protected CommonDao(ConnectionPool pool) {
        this.pool = pool;
        this.selectAllExpression = format(SELECT_ALL_FROM, String.join(", ", getFields())) + getTableName();
        this.selectByIdExpression = selectAllExpression + SPACE + format(WHERE_FIELD, getIdFieldName());
    }

    @Override
    public List<T> read() {
        try {
            return executeStatement(selectAllExpression,
                    this::extractResultCatchingException);
        } catch (InterruptedException e) {
            LOGGER.info("takeConnection interrupted", e);
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<T> read(Long id) {
        try {
            return executePreparedForGenericEntity(selectByIdExpression,
                    this::extractResultCatchingException,
                    st -> st.setLong(1, id));
        } catch (InterruptedException e) {
            LOGGER.info("takeConnection interrupted", e);
            Thread.currentThread().interrupt();
            return Optional.empty();
        }
    }


    protected <T extends Entity> List<T> executeStatement(String sql, ResultSetExtractor<T> extractor) throws InterruptedException {
        try (final Connection connection = pool.takeConnection();
             final Statement statement = connection.createStatement()) {
            final ResultSet resultSet = statement.executeQuery(sql);
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOGGER.error("sql exception occurred", e);
            LOGGER.debug("sql: {}", sql);
        } catch (EntityExtractionFailedException e) {
            LOGGER.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }


    protected <G> Optional<G> executePreparedForGenericEntity(String sql,
                                                              ResultSetExtractor<G> extractor,
                                                              StatementPreparator statementPreparation)
            throws InterruptedException {
        try (final Connection connection = pool.takeConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            if (statementPreparation != null) {
                statementPreparation.accept(statement);
            }
            final ResultSet resultSet = statement.executeQuery();

            return resultSet.next()
                    ? Optional.ofNullable(extractor.extract(resultSet))
                    : Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("sql exception occurred", e);
            LOGGER.debug("sql: {}", sql);
        } catch (EntityExtractionFailedException e) {
            LOGGER.error("could not extract entity", e);
        }
        return Optional.empty();
    }

    protected <T extends Entity> List<T> executePreparedForEntities(String sql,
                                                                    ResultSetExtractor<T> extractor,
                                                                    StatementPreparator statementPreparation) throws InterruptedException {
        try (final Connection connection = pool.takeConnection();
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


    protected T extractResultCatchingException(ResultSet rs) throws EntityExtractionFailedException {
        try {

            return extractResult(rs);
        } catch (SQLException e) {
            LOGGER.error("sql exception occurred extracting entity from ResultSet", e);
            throw new EntityExtractionFailedException("could not extract entity", e);
        }
    }

    protected abstract String getTableName();

    protected abstract List<String> getFields();

    protected abstract String getIdFieldName();

    protected abstract T extractResult(ResultSet rs) throws SQLException;

}

