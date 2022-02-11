package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.core.MentorDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Mentor;

public interface MentorDao extends EntityDao<Mentor> {

    static MentorDao instance() {
        return MentorDaoImpl.getInstance();
    }

}
