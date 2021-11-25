package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.EntityDao;
import com.epam.jwd.onlinetraining.dao.connectionpool.api.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.model.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class AccountDaoImpl implements EntityDao<Account> {
    private static final Logger LOGGER = LogManager.getLogger(AccountDaoImpl.class);
    private static final String SQL_SAVE_ACCOUNT = "INSERT INTO account(email, password) VALUES (?, ?); ";
    public static final String SQL_UPDATE_ACCOUNT = "UPDATE account SET password=? WHERE id_account=?";
    private static final String SQL_DELETE_ACCOUNT = "DELETE FROM account  WHERE id_account=?";
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    public AccountDaoImpl() {

    }

//    public AccountDaoImpl(/*ConnectionPool connectionPool*/) {
//      this.pool=conenctionPool;
//    }

    public static void main(String[] args) {

    }

    @Override
    public Account save(Account account) {
        LOGGER.debug("save account to database");
        try (Connection connection = pool.requestConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();

            List<Account> accountList = new ArrayList<>();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    account.setId(id);
                }
            }

        } catch (SQLException exception) {
            LOGGER.error("sql exception in save method", exception);
            exception.printStackTrace();
        }
        LOGGER.debug("account is saved successfully");
        return account;
    }


    @Override
    public Boolean update(Account account) {
        Boolean result = false;
        try (final Connection connection = pool.requestConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ACCOUNT);) {
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getEmail());
            result = Objects.equals(preparedStatement.executeUpdate(), 1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

//
//    public Boolean delete(Account account) {
//        Boolean result = false;
//        try (final Connection connection = pool.requestConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ACCOUNT);) {
//            preparedStatement.setInt(1, account.getId());
//            result = Objects.equals(preparedStatement.executeUpdate(), 1);
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//        return result;
//    }

    public Account findById(Integer id) {
        Account account = new Account();
        return account;
    }



    @Override
    public List<Account> read() {
        return null;
    }

    @Override
    public Optional<Account> read(Long id) {
        return Optional.empty();
    }

}