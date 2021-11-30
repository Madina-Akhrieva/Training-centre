package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Entity;
import com.epam.jwd.onlinetraining.dao.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl extends CommonDao<Course> implements CourseDao{
    private static final Logger LOGGER = LogManager.getLogger(CourseDaoImpl.class);
    private static final String SQL_SAVE_COURSE = "INSERT INTO course (mentor_id, title, amount_of_tasks, learning_language, description) values(?,?,?,?,?) ";
    private static final String SQL_DELETE_COURSE = "DELETE FROM course WHERE  id VALUE (?)";
    public static final String COURSE = "course";
    private static final List<String> FIELDS = Arrays.asList(
            "title", "amount_of_tasks", "learning_language", "description"
    );
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();


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
            LOGGER.error("SQLException exception occurred", exception);
        }
        return course;
    }



    @Override
    protected String getTableName() {
        return COURSE;
    }

    @Override
    protected List<String> getFields() {
        return FIELDS;
    }

    @Override
    protected String getIdFieldName() {
        return null;
    }

    @Override
    protected Course extractResult(ResultSet rs) throws EntityExtractionFailedException {
        try {
            return new Course(
                    rs.getString("title"),
                    rs.getInt("amount_of_tasks"),
                    rs.getString("learning_language"),
                    rs.getString("description")
            );
        } catch (SQLException e) {
            LOGGER.error("sql exception occurred extracting course from ResultSet",e);
            throw  new EntityExtractionFailedException("sql exception occurred extracting course from ResultSet",e);
        }
    }



    @Override
    protected void fillEntity(PreparedStatement statement, Course entity) throws SQLException {

    }


    @Override
    public Boolean insert(Course entity) {
        return null;
    }

    @Override
    public Course findById(Long id) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Optional<Course> read(Long id) {
        return Optional.empty();
    }


    public static CourseDao getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final CourseDao INSTANCE =  new CourseDaoImpl();
    }

}
