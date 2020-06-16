package com.androiddev.mobile_ws.userservice;

import com.androiddev.mobile_ws.model.request.UserDetailsRequestModel;
import com.androiddev.mobile_ws.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetail);
}
