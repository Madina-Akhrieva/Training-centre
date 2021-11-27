package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.impl.AccountDaoImpl;
import com.epam.jwd.onlinetraining.dao.impl.UserDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.api.EntityService;


import java.util.List;

public class UserService implements EntityService<User> {

    private AccountDaoImpl accountDaoImpl = new AccountDaoImpl();

    private UserDaoImpl userDaoImpl = new UserDaoImpl();


    public User save(User user) {
        try {
            Account account = accountDaoImpl.save(user.getAccount());
            user.setAccount(account);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return userDaoImpl.save(user);
    }

    public Boolean update(User user) {
        Boolean isUpdated = false;
        try {
            isUpdated = accountDaoImpl.update(user.getAccount());
            if (isUpdated == true) {
                isUpdated = userDaoImpl.update(user);
                userDaoImpl.update(user);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return isUpdated;
    }



    @Override
    public List<User> findAll() {

        return null;
    }


}
