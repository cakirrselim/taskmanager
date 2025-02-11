package com.selim.taskmanager.data;

import com.selim.taskmanager.entitiy.Users;

import java.util.List;

public interface UsersDao {
    List<Users> getAllUsers();
    Users addUser(Users user);
    void updateUser(Users user);
    void deleteUser(int id);

    Users findByUsername(String username);
    Users findByEmail(String email);


}
