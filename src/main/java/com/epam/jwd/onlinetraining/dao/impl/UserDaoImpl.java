package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.EntityDao;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.model.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


public class UserDaoImpl implements EntityDao<User> {
    private static final String SQL_SAVE_USER = "INSERT INTO course_user(id_course_user, role_id, phone, first_name, last_name) VALUES ( ?, ?, ?, ?, ?)";
    private static final String SQL_EDIT_USER = "UPDATE course_user SET   id_course_user = ?, role_id = ?, phone = ?, first_name=?, last_name=? WHERE id_course_user = ?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT role_id, phone, first_name, last_name FROM course_user WHERE id_course_user = ?";
    private static final String SQL_FIND_ALL_USERS = "SELECT role_id, phone, first_name, last_name FROM course_user";
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    //private  Account account = new Account();

    //public UserDaoImpl(){}
//    public UserDaoImpl(ConnectionPool connectionPool) {
//        this.pool = connectionPool;
//    }

//    private UserServiceImpl userService = new UserServiceImpl();


    public User save(User user) {
        try (final Connection connection = pool.requestConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getRoleId());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    user.setId(id);
                }
            }
        } catch (SQLException exception) {
            //todo:add log
            exception.printStackTrace();
        }
        return user;
    }

    @Override
    public Boolean insert(User entity) {
        return null;
    }

    @Override
    public Boolean update(User user) {
        Boolean result = false;
        try (Connection connection = pool.requestConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_EDIT_USER);) {
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setInt(2, user.getRoleId());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setInt(6,user.getId());
            preparedStatement.executeUpdate();

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                result = true;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            //todo:add logger
        }
        return result;

    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public List<User> read() {
        return null;
    }

    @Override
    public Optional<User> read(Long id) {
        return Optional.empty();
    }


    public List<User> findAll() {
        try(Connection connection = pool.requestConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS)){

        } catch (SQLException exception) {
            exception.printStackTrace();
        }


        return null;
    }

//    public User findById(Integer id) {
//        Connection connection;
//        PreparedStatement preparedStatement;
//        try {
//
//            connection = pool.requestConnection();
//
//            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                User user = new User(resultSet.getInt(1),
//                        resultSet.getInt(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5));
//                resultSet.close();
//                System.out.printf(user.toString());
//                preparedStatement.close();
//                return user;
//            } else {
//                resultSet.close();
//
//            }
//        } catch (SQLException exception) {
//            //todo:add log
//            exception.printStackTrace();
//        }
//
//        return null;
//    }


}
