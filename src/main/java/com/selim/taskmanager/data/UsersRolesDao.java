package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.entity.UsersRoles;

import java.util.List;
import java.util.UUID;

public interface UsersRolesDao {


    void assignRoleToUser(int userId, UUID roleId);

    List<Role> getRolesByUserId(int userId);

}
