package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.AccountDao;
import com.epam.jwd.onlinetraining.dao.api.EntityDao;
import com.epam.jwd.onlinetraining.dao.api.UserDao;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.db.LockingConnectionPool;
import com.epam.jwd.onlinetraining.dao.model.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public class AccountDaoImpl extends CommonDao<Account> implements AccountDao {

    private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);
    private static final String EMAIL_FIELD_NAME = "email";
    private static final String PASSWORD_FIELD_NAME = "account_password";
    private static final String ACCOUNT_TABLE_NAME = "account";

    private final String selectByEmailExpression;

    protected AccountDaoImpl(ConnectionPool pool) {
        super(pool);
        this.selectByEmailExpression = CommonDao.SELECT_ALL_FROM + getTableName() + SPACE  + format(WHERE_FIELD, EMAIL_FIELD_NAME);
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
        return ACCOUNT_TABLE_NAME;
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
    protected Account extractResult(ResultSet rs) throws SQLException {
        return new Account(
                rs.getString(PASSWORD_FIELD_NAME),
                rs.getString(EMAIL_FIELD_NAME)
        );
    }

    @Override
    protected void fillEntity(PreparedStatement statement, Account entity) throws SQLException {

    }


    @Override
    public Optional<Account> readAccountByEmail(String email) {
        try {
            return executePreparedForGenericEntity(selectByEmailExpression,
                    this::extractResultCatchingException,
                    st -> st.setString(1, email));
        } catch (InterruptedException e) {
            LOGGER.info("take connection interrupted", e);
            return Optional.empty();
        }
    }

    public static AccountDao getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final AccountDao INSTANCE = new AccountDaoImpl(ConnectionPool.instance());
    }


}