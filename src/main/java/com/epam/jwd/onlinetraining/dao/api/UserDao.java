package com.epam.jwd.onlinetraining.dao.api;

import com.epam.jwd.onlinetraining.dao.model.User;

import java.util.List;

public interface UserDao extends EntityDao<User> {

    List<User> findByLastName(String lastName);

}
