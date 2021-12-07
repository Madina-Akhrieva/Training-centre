package com.epam.jwd.onlinetraining.dao.impl;

import com.epam.jwd.onlinetraining.dao.api.MentorDao;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.model.Mentor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public final class MentorDaoImpl extends CommonDao<Mentor> implements MentorDao {
    private static final Logger LOGGER = LogManager.getLogger(MentorDaoImpl.class);

    private static final List<String> FIELDS = Arrays.asList(
            "id","experience", "position", "pen_name"
    );

    public static final String MENTOR_TABLE_NAME = "mentor";

    protected MentorDaoImpl(ConnectionPool pool) {
        super(pool);
    }

    @Override
    protected String getTableName() {
        return MENTOR_TABLE_NAME;
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
    protected Mentor extractResult(ResultSet rs) throws SQLException {
        return new Mentor(
                rs.getLong("id"),
//                rs.getString("phone"),
//                rs.getString("first_name"),
//                rs.getString("last_name"),
                rs.getInt("experience"),
                rs.getString("position"),
                rs.getString("pen_name")
        );
    }

    @Override
    protected void fillEntity(PreparedStatement statement, Mentor entity) {

    }


    public static MentorDao getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final MentorDao INSTANCE = new MentorDaoImpl(ConnectionPool.instance());
    }

}
