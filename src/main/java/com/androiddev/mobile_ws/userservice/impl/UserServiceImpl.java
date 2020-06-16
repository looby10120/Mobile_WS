package com.androiddev.mobile_ws.userservice.impl;

import com.androiddev.mobile_ws.model.request.UserDetailsRequestModel;
import com.androiddev.mobile_ws.model.response.UserRest;
import com.androiddev.mobile_ws.shared.Utils;
import com.androiddev.mobile_ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class UserServiceImpl implements UserService {

    HashMap<String, UserRest> users;

    Utils utils;

    public UserServiceImpl() {}

    @Autowired
    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetail) {

        UserRest result = new UserRest();

        result.setFirstName(userDetail.getFirstName());
        result.setLastName(userDetail.getLastName());
        result.setEmail(userDetail.getEmail());
        result.setUserId(userDetail.getPassword());

        String userId = utils.generateUserId();
        result.setUserId(userId);

        if (users == null) users = new HashMap<>();
        users.put(userId, result);

        return result;
    }

}
