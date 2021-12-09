package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.impl.UserDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends EntityDao<User> {

    static UserDao instance() {
        return UserDaoImpl.getInstance();
    }


}
