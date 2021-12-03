package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.impl.AccountDaoImpl;
import com.epam.jwd.onlinetraining.dao.impl.UserDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.api.EntityService;


import java.util.List;

public class UserService implements EntityService<User> {

    @Override
    public List<User> findAll() {
        return null;
    }


}
