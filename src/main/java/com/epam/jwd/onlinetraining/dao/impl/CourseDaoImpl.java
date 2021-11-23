package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.connectionpool.api.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.impl.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.dao.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl extends Entity<Integer> implements CourseDao<CourseDaoImpl, Integer>{
    private static final Logger LOGGER = LogManager.getLogger(CourseDaoImpl.class);
    private static final String SQL_SAVE_COURSE = "INSERT INTO course (mentor_id, title, amount_of_tasks, learning_language, description) values(?,?,?,?,?) ";
    private static final String SQL_DELETE_COURSE = "DELETE FROM course WHERE  id VALUE (?)";
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

    @Override
    public Course save(Course course) {
        LOGGER.debug("Start save method");
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
            LOGGER.error("SQLException exception occured", exception);
        }
        return course;
    }

    @Override
    public Boolean delete(Integer id) {
        try(Connection connection = pool.requestConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COURSE)){

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> read() {
        return null;
    }

    @Override
    public Optional<Course> read(Integer id) {
        return Optional.empty();
    }

    @Override
    public Integer findByTitle(String title) {
        return null;
    }

    @Override
    public Integer findByLanguage(String language) {
        return null;
    }

    @Override
    public Boolean update(Course entity) {
        return null;
    }

    @Override
    public Course findById(Integer id) {
        return null;
    }
}
