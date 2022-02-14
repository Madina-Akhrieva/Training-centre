package com.epam.jwd.onlinetraining.dao.core;

import com.epam.jwd.onlinetraining.dao.api.MentorDao;
import com.epam.jwd.onlinetraining.dao.db.ConnectionPool;
import com.epam.jwd.onlinetraining.dao.model.Mentor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * com.epam.jwd.onlinetraining.dao.core public final class MentorDaoImpl
 * extends CommonDao<Mentor>
 * implements MentorDao
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public final class MentorDaoImpl extends CommonDao<Mentor> implements MentorDao {
    private static final Logger LOGGER = LogManager.getLogger(MentorDaoImpl.class);
    private static final String MENTOR_TABLE_NAME = "mentor";
    private static final String ID_FIELD_NAME = "id";
    private static final String EXPERIENCE_FIELD_NAME = "experience";
    private static final String POSITION_FIELD_NAME = "position";
    private static final String PEN_NAME_FIELD_NAME = "pen_name";
    private static final List<String> FIELDS = Arrays.asList(
            "id", "experience", "position", "pen_name"
    );

    public static MentorDao getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Mentor create(Mentor entity) {
        return null;
    }

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
        return ID_FIELD_NAME;
    }

    @Override
    protected Mentor extractResult(ResultSet rs) throws SQLException {
        return new Mentor(
                rs.getLong(ID_FIELD_NAME),
                rs.getInt(EXPERIENCE_FIELD_NAME),
                rs.getString(POSITION_FIELD_NAME),
                rs.getString(PEN_NAME_FIELD_NAME)
        );
    }

    private static class Holder {
        public static final MentorDao INSTANCE = new MentorDaoImpl(ConnectionPool.instance());
    }



}
