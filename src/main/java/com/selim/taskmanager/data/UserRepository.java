package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Users;


public interface UserRepository {
    Users findByUsernameAndPassword(String username, String password);
}


