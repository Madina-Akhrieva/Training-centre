package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.UserDao;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.model.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends CommonDao<User> implements UserDao {
    private static final String SQL_SAVE_USER = "INSERT INTO course_user(id_course_user, role_id, phone, first_name, last_name) VALUES ( ?, ?, ?, ?, ?)";
    private static final String SQL_EDIT_USER = "UPDATE course_user SET   id_course_user = ?, role_id = ?, phone = ?, first_name=?, last_name=? WHERE id_course_user = ?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT role_id, phone, first_name, last_name FROM course_user WHERE id_course_user = ?";
    private static final String SQL_FIND_ALL_USERS = "SELECT role_id, phone, first_name, last_name FROM course_user";

    protected UserDaoImpl(ConnectionPool pool) {
        super(pool);
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
    protected User extractResult(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    protected void fillEntity(PreparedStatement statement, User entity) throws SQLException {

    }

    public List<User> findAll() throws InterruptedException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS)) {

        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        return null;
    }


    public static UserDao getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public boolean update(User entity, String param) {
        return false;
    }


    private static class Holder {
        public static final UserDao INSTANCE = new UserDaoImpl(ConnectionPool.instance());
    }

}
