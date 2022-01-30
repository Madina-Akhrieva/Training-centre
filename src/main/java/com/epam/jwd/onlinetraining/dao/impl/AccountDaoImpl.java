package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.AccountDao;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.lang.String.join;

public class AccountDaoImpl extends CommonDao<Account> implements AccountDao {

    private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);
    private static final String ACCOUNT_TABLE_NAME = "account j join role r on r.id_role = j.role_id";
    public static final String ID_FIELD_NAME = "j.id";
    private static final String EMAIL_FIELD_NAME = "j.email";
    private static final String PASSWORD_FIELD_NAME = "j.account_password";
    private static final String ROLE_FIELD_NAME = "r.name";
    public static final String SET_FOREIGN_KEYS_0 = "SET foreign_key_checks = 0";
    public static final String SET_FOREIGN_KEYS_1 = "SET foreign_key_checks = 1";

    private static final List<String> FIELDS = Arrays.asList(
            ID_FIELD_NAME, EMAIL_FIELD_NAME,
            PASSWORD_FIELD_NAME, ROLE_FIELD_NAME
    );

    private static final List<String> INSERT_FIELDS = Arrays.asList(
            EMAIL_FIELD_NAME,
            PASSWORD_FIELD_NAME
    );
    private static final String INSERT_ACCOUNT = "insert into account  ( account_password, email) values(?, ?)";


    private final String insertSql;
    private final String selectByEmailExpression;
    private final String selectByIdExpression;

    protected AccountDaoImpl(ConnectionPool pool) {
        super(pool);
        this.selectByIdExpression = format(SELECT_ALL_FROM, join(", ", getFields())) + getTableName() + SPACE + format(WHERE_FIELD, ID_FIELD_NAME);
        this.selectByEmailExpression = format(SELECT_ALL_FROM, join(", ", getFields())) + getTableName() + SPACE + format(WHERE_FIELD, EMAIL_FIELD_NAME);
        this.insertSql = "insert into account  ( account_password, email) values(?, ?)";

    }


    @Override
    protected String getTableName() {
        return ACCOUNT_TABLE_NAME;
    }

    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    @Override
    protected String getIdFieldName() {
        return ID_FIELD_NAME;
    }

    @Override
    protected Account extractResult(ResultSet rs) throws SQLException {
        return new Account(
                rs.getLong(ID_FIELD_NAME),
                rs.getString(PASSWORD_FIELD_NAME),
                rs.getString(EMAIL_FIELD_NAME),
                Role.of(rs.getString(ROLE_FIELD_NAME))
        );
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

    @Override
    public Optional<Account> findByMail(String mail) {
        return Optional.empty();
    }

    @Override
    public Optional<Account> readAccountById(long id) {
        try {
            return executePreparedForGenericEntity(selectByIdExpression,
                    this::extractResultCatchingException,
                    st -> st.setLong(1, id));
        } catch (InterruptedException e) {
            LOGGER.info("take connection interrupted", e);
            return Optional.empty();
        }
    }

    public static AccountDao getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Account create(Account account) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet;
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                account.setId(resultSet.getLong(1));
            }
        } catch (InterruptedException | SQLException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public boolean update(Account entity, String param) {
        return false;
    }

    private static class Holder {
        public static final AccountDao INSTANCE = new AccountDaoImpl(ConnectionPool.instance());
    }


}