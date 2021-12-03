package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.CourseDao;
import com.epam.jwd.onlinetraining.dao.api.EntityDao;
import com.epam.jwd.onlinetraining.dao.api.MentorDao;
import com.epam.jwd.onlinetraining.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.onlinetraining.dao.model.Course;
import com.epam.jwd.onlinetraining.dao.model.Mentor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public final class MentorDaoImpl extends CommonDao<Mentor> implements MentorDao {

    public static final String MENTOR_TABLE_NAME = "mentor";

    @Override
    protected String getTableName() {
        return MENTOR_TABLE_NAME;
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
    protected Mentor extractResult(ResultSet rs) throws SQLException{
        return new Mentor(
                rs.getLong("id_mentor"),
                rs.getString("phone"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getInt("experience"),
                rs.getString("position")
        );
    }

    @Override
    protected void fillEntity(PreparedStatement statement, Mentor entity) throws SQLException {

    }


    public static MentorDao getInstance() {
        return MentorDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final MentorDao INSTANCE = new MentorDaoImpl();
    }

}
