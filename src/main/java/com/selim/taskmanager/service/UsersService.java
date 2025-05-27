package com.selim.taskmanager.service;

import com.selim.taskmanager.rest.model.GetUsersByUserIdModel;
import com.selim.taskmanager.rest.model.UsersAddRequestModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;
import com.selim.taskmanager.rest.model.UsersShowResponseModel;

import java.util.List;
import java.util.UUID;

public interface UsersService {

    List<UsersShowResponseModel> getAllUsers();
    UsersAddResponseModel addUser(UsersAddRequestModel usersAddRequestModel);
    void updateUser(UsersAddRequestModel usersAddRequestModel);
    void deleteUser(int id);
    UsersAddResponseModel findByUsername(String username);
    UsersAddResponseModel findByEmail(String email);
    List<GetUsersByUserIdModel> getUsersByRoleId(UUID roleId);  // BURDAYIZ




}
