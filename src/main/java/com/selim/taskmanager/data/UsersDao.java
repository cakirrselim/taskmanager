package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Users;
import java.util.List;
import java.util.UUID;

public interface UsersDao {
    Users addUser(Users user);
    void updateUser(Users user);
    void deleteUser(int id);
    Users findByUsername(String username);
    Users findByEmail(String email);
    List<Users> getAllUsers();
    List<Users> getUsersByRoleId(UUID roleId);

}
