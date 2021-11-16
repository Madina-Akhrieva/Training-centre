package com.epam.jwd.onlinetraining.service.impl;

import com.epam.jwd.onlinetraining.dao.impl.AccountDaoImpl;
import com.epam.jwd.onlinetraining.dao.impl.UserDaoImpl;
import com.epam.jwd.onlinetraining.dao.model.Account;
import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.api.Service;
import com.epam.jwd.onlinetraining.service.conveter.UserConverter;
import com.epam.jwd.onlinetraining.service.dto.UserDto;
import com.epam.jwd.onlinetraining.service.validator.UserValidator;
import com.epam.jwd.onlinetraining.service.validator.Validator;

public class UserService implements Service<UserDto, Integer> {
    //todo:check
    private UserConverter converter = new UserConverter();
    private Validator<UserDto> validator = new UserValidator();

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
    public UserDto create(UserDto user) {
        validator.validate(user);
        return converter.convert(userDaoImpl.save(converter.convert(user)));

    }


    @Override
    public UserDto update(UserDto entity) {
        return null;
    }

    @Override
    public boolean delete(UserDto entity) {
        return false;
    }

    @Override
    public UserDto getById(UserDto entity) {
        return null;
    }

    @Override
    public UserDto getAll(UserDto entity) {
        return null;
    }

}
