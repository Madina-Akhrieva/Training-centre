package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.connectionpool.ConnectionPoolImpl;
import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class CourseDaoImpl extends CommonDao<Course> implements CourseDao {

    private static final Logger LOGGER = LogManager.getLogger(CourseDaoImpl.class);

    private static final String SQL_SAVE_COURSE = "INSERT INTO course (mentor_id, title, amount_of_tasks, learning_language, description) values(?,?,?,?,?) ";
    private static final String SQL_DELETE_COURSE = "DELETE FROM course WHERE  id VALUE (?)";
    public static final String COURSE = "course";
    private static final List<String> FIELDS = Arrays.asList(
            "title", "amount_of_tasks", "learning_language", "description"
    );
    private ConnectionPool pool = ConnectionPoolImpl.getInstance();

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
    protected Course extractResult(ResultSet rs) throws SQLException {
        return new Course(
                rs.getString("title"),
                rs.getInt("amount_of_tasks"),
                rs.getString("learning_language"),
                rs.getString("description")
        );
    }


    @Override
    protected void fillEntity(PreparedStatement statement, Course entity) {

    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public Optional<Course> read(Long id) {
        return Optional.empty();
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

        return executePreparedForGenericEntity("select id_mentor from course c where c.id_course = ?",
                rs -> extractUserId(rs), st -> st.setLong(1, id));


    }

    private long extractUserId(ResultSet rs) throws EntityExtractionFailedException {
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

    private static class Holder {
        public static final CourseDao INSTANCE = new CourseDaoImpl();
    }

}
