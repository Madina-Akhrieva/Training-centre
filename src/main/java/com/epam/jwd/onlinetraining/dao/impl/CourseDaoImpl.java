package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class CourseDaoImpl extends CommonDao<Course> implements CourseDao {

    private static final Logger LOGGER = LogManager.getLogger(CourseDaoImpl.class);

    private static final String SQL_DELETE_COURSE = "DELETE FROM course WHERE  id=,";
    private static final String INSERT_COURSE = "INSERT INTO course( title, learning_language, description) values(?,?,?)  ";
    private static final String SELECT_TITLE_LEARNING_LANGUAGE_DESCRIPTION_FROM_COURSE__WHERE__ID = "select title, learning_language, description from course c where c.id = ?";
    private static final String SELECT_MENTOR_ID_FROM_COURSE = "select mentor_id from course c where c.id = ?";

    private static final String COURSE = "course";
    private static final String ID_FIELD_NAME = "id";
    private static final String TITLE_FIELD_NAME = "title";
    private static final String DESCRIPTION_FIELD_NAME = "description";
    private static final String TITLE_COLUMN_NAME = "title";
    private static final String LEARNING_LANGUAGE_COLUMN_NAME = "learning_language";
    private static final String DESCRIPTION_COLUMN_NAME = "description";

    private static final List<String> FIELDS = Arrays.asList(
            "id", "title", "learning_language", "description"
    );
    private static final String UPDATE_COURSE_WHERE_TITLE = "UPDATE course SET title = ?, learning_language = ?, description = ? WHERE title = ?";
    public static final String COURSE_ID_COLUMN_NAME = "id";


    protected CourseDaoImpl(ConnectionPool pool) {
        super(pool);
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
        return ID_FIELD_NAME;
    }


    @Override
    protected Course extractResult(ResultSet rs) throws SQLException {
        return new Course(
                rs.getLong(ID_FIELD_NAME),
                rs.getString(TITLE_FIELD_NAME),
                rs.getString(LEARNING_LANGUAGE_COLUMN_NAME),
                rs.getString(DESCRIPTION_FIELD_NAME)
        );
    }


    @Override
    protected void fillEntity(PreparedStatement statement, Course entity) {

    }

    @Override
    public List<Course> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Course> findByLanguage(String language) {
        return null;
    }

    @Override
    public Optional<Long> findMentorIdByCourseID(Long id) {

        try {
            return executePreparedForGenericEntity(SELECT_MENTOR_ID_FROM_COURSE,
                    this::extractMentorId,
                    st -> st.setLong(1, id));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Course findCourseByCourseID(Long id) {
        Course course = null;
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     SELECT_TITLE_LEARNING_LANGUAGE_DESCRIPTION_FROM_COURSE__WHERE__ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString(TITLE_COLUMN_NAME);
                String learning_language = resultSet.getString(LEARNING_LANGUAGE_COLUMN_NAME);
                String description = resultSet.getString(DESCRIPTION_COLUMN_NAME);

                course = new Course(id, title, learning_language, description);
            }
        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return course;
    }


    @Override
    public boolean update(Course course, String title) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     UPDATE_COURSE_WHERE_TITLE)) {
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getLearningLanguage());
            preparedStatement.setString(3, course.getDescription());
            preparedStatement.setString(4, title);

            boolean rowUpdated = preparedStatement.executeUpdate() > 0;
            return rowUpdated;

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        return false;
    }


    private long extractMentorId(ResultSet rs) throws EntityExtractionFailedException {
        try {
            return rs.getLong("mentor_id");
        } catch (SQLException e) {
            LOGGER.error("sql exception occurred extracting entity from ResultSet", e);
            throw new EntityExtractionFailedException("couldn't extract entity", e);
        }
    }


    public static CourseDao getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }


    @Override
    public Course create(Course course) {
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet;
            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setString(2, course.getLearningLanguage());
            preparedStatement.setString(3, course.getDescription());
//            preparedStatement.setLong(4, course.getMentor().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                course.setId(resultSet.getLong(1));
            }
        } catch (InterruptedException e) {
            LOGGER.warn("exception", e);
            e.printStackTrace();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return course;
    }


    private static class Holder {
        public static final CourseDao INSTANCE = new CourseDaoImpl(ConnectionPool.instance());
    }

}
