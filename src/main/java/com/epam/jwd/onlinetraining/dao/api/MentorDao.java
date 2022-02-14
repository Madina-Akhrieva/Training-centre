package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.core.MentorDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Mentor;

/**
 * com.epam.jwd.onlinetraining.dao.api public interface MentorDao
 * extends EntityDao<Mentor>
 *
 * @author Madina Akhrieva
 * @version 1.0
 */
public interface MentorDao extends EntityDao<Mentor> {

    static MentorDao instance() {
        return MentorDaoImpl.getInstance();
    }

}
