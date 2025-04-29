package com.selim.taskmanager.service;

import com.selim.taskmanager.rest.model.*;

import java.util.List;
import java.util.UUID;

public interface UsersRolesService {

    void assignRoleToUser(int userId, UUID roleId);
    void deleteUserFromRole(int userId, UUID roleId);
    List<RoleShowResponseModel> getRolesByUserId(int userId);
}
