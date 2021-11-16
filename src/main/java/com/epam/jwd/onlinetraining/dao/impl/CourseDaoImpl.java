package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.api.Dao;
import com.epam.jwd.onlinetraining.dao.connectionpool.api.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.model.AbstractEntity;
import com.epam.jwd.onlinetraining.dao.model.Course;

import java.sql.*;

public class CourseDaoImpl extends AbstractEntity<Integer> implements CourseDao<CourseDaoImpl, Integer>, Dao<Course, Integer> {
    private static final String SQL_SAVE_COURSE = "INSERT INTO course (mentor_id, title, amount_of_tasks, learning_language, description) values(?,?,?,?,?) ";
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    @Override
    public Course save(Course course) {
        try (Connection connection = pool.requestConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_COURSE, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setInt(1, course.getMentorId());
            preparedStatement.setString(2, course.getTitle());
            preparedStatement.setInt(3, course.getAmountOfTasks());
            preparedStatement.setString(4, course.getLearningLanguage());
            preparedStatement.setString(5, course.getDescription());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    course.setId(id);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(Integer id) {

        return null;
    }

    @Override
    public Boolean update(Course entity) {
        return null;
    }
}
