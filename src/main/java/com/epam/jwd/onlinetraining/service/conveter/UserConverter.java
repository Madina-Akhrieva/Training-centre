package com.epam.jwd.onlinetraining.service.conveter;

import com.epam.jwd.onlinetraining.dao.model.User;
import com.epam.jwd.onlinetraining.service.dto.UserDto;

public class UserConverter {
    public UserDto convert(User user){
        //todo:add users fields
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        return userDto;
    }

    public User convert(UserDto userDto){
        //todo:add users fields
        User user = new User();
        userDto.setId(user.getId());
        return user;
    }
}
