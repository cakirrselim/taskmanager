package com.selim.taskmanager.service;

import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.rest.model.LoginModel;

public interface UserService {
    Users login(String username, String password);
}

