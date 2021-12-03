package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.EntityDao;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AccountDaoImpl extends CommonDao<Account> implements EntityDao<Account> {

    private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);

    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    public AccountDaoImpl() {

    }

    @Override
    public Account create(Account entity) {
        return null;
    }

    @Override
    public Account update(Account entity) {
        return null;
    }


    @Override
    public List<Account> read() {
        return null;
    }

    @Override
    public Optional<Account> read(Long id) {
        return Optional.empty();
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected List<String> getFields() {
        return null;
    }

    @Override
    protected String getIdFieldName() {
        return null;
    }

    @Override
    protected Account extractResult(ResultSet rs) throws SQLException{
        return null;
    }

    @Override
    protected void fillEntity(PreparedStatement statement, Account entity) throws SQLException {

    }


}