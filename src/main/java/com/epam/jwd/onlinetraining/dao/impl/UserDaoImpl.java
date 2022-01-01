package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.UserDao;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends CommonDao<User> implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String INSERT_USER = "INSERT INTO course_user(phone, first_name, last_name,id ) VALUES (?, ?, ?, ?)";
    private static final String SQL_EDIT_USER = "UPDATE course_user SET   id_course_user = ?, role_id = ?, phone = ?, first_name=?, last_name=? WHERE id_course_user = ?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT role_id, phone, first_name, last_name FROM course_user WHERE id_course_user = ?";
    private static final String SQL_FIND_ALL_USERS = "SELECT role_id, phone, first_name, last_name FROM course_user";
    private static final String FIELD_NAME = "field_name";
    public static final String SELECT_USER_FROM_COURSE_WHERE_ID = "select id, phone, first_name, last_name from course_user where id=?";
    private static final String INSERT_COURSE_ID_AND_USER_ID_INTO_SUBSCRIPTION = "insert into subscription ( course_id, course_user_id) values (?, ?)";
    public static final String COURSE_USER_TABLE_NAME = "course_user";
    public static final String ID_COLUMN_NAME = "id";
    public static final String COURSE_ID_COLUMN_NAME = "c.id";
    public static final String PHONE_COLUMN_NAME = "phone";
    public static final String FIRST_NAME_COLUMN_NAME = "first_name";
    public static final String LAST_NAME_COLUMN_NAME = "last_name";

    private static final String SELECT_COURSE_ID_USER_ID_WHERE_COURSE_ID_USER_ID = "select course_user_id, course_id from subscription where course_id=? and course_user_id=?";
    private static final String INNER_LOIN_SELECT_FROM_COURSE_AND_COURSE_USER = "select cu.id, first_name, last_name, c.id, title from subscription " +
            "inner join course c on c.id = subscription.course_id\n" +
            "inner join course_user cu on  cu.id = subscription.course_user_id where course_user_id = ?";
    private static final String ACCOUNT_TABLE_NAME = "account j join role r on r.id_role = j.role_id";
    private static final List<String> FIELDS = Arrays.asList(
            "id", "phone", "first_name", "last_name"
    );

    private static final String USER_ID_COLUMN_NAME = "";
    private static final String TITLE_COLUMN_NAME = "title";

    protected UserDaoImpl(ConnectionPool pool) {
        super(pool);
    }

    @Override
    protected String getTableName() {
        return COURSE_USER_TABLE_NAME;
    }

    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    @Override
    protected String getIdFieldName() {
        return FIELD_NAME;
    }

    @Override
    protected User extractResult(ResultSet rs) {
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
    public User create(User user) {
        ResultSet resultSet;
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getPhone());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean update(User entity, String param) {
        return false;
    }

    @Override
    public Optional<User> readUserByPhone(String phone) {
        return Optional.empty();
    }

    @Override
    public User findById(long id) {
        User user = null;
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SELECT_USER_FROM_COURSE_WHERE_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long user_id = resultSet.getLong(ID_COLUMN_NAME);
                String phone = resultSet.getString(PHONE_COLUMN_NAME);
                String first_name = resultSet.getString(FIRST_NAME_COLUMN_NAME);
                String last_name = resultSet.getString(LAST_NAME_COLUMN_NAME);

                user = new User(user_id, phone, first_name, last_name);
            }
        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return user;
    }

    @Override
    public boolean addCourseToUser(long courseId, long userId) {
        ResultSet resultSet;
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     INSERT_COURSE_ID_AND_USER_ID_INTO_SUBSCRIPTION)) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean readSubscriptionByCourseIdAndUserId(long courseId, long userId) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SELECT_COURSE_ID_USER_ID_WHERE_COURSE_ID_USER_ID)) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean subscriptionExists = false;
            if (resultSet.next()) {
                subscriptionExists = true;
            }
            return subscriptionExists;

        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Course> findAllCoursesByUserId(long userId) {
        List<Course> courses = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     INNER_LOIN_SELECT_FROM_COURSE_AND_COURSE_USER)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString(TITLE_COLUMN_NAME);
                Long courseId = resultSet.getLong(COURSE_ID_COLUMN_NAME);
                courses.add(new Course(courseId,title));
            }
        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return courses;
    }


    private static class Holder {
        public static final UserDao INSTANCE = new UserDaoImpl(ConnectionPool.instance());
    }

}
