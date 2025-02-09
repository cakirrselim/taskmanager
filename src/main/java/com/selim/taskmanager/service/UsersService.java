package com.selim.taskmanager.service;

import com.selim.taskmanager.rest.model.UsersAddRequestModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;

import java.util.List;

public interface UsersService {

    List<UsersAddResponseModel> getAllUsers();
    UsersAddResponseModel addUser(UsersAddRequestModel usersAddRequestModel);
    void updateUser(UsersAddRequestModel usersAddRequestModel);
    void deleteUser(int id);
    UsersAddResponseModel findByUsername(String username);
    UsersAddResponseModel findByEmail(String email);


}
