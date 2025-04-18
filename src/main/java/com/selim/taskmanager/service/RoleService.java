package com.selim.taskmanager.service;

import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.rest.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleAddResponseModel addRole(RoleAddRequestModel roleAddRequestModel);
    void deleteRole(UUID roleId);
    void updateRole(RoleUpdateRequestModel roleUpdateRequestModel);
    List<RoleShowResponseModel2> getAllRoles();
    RoleAddResponseModel getRoleByName(String roleName);
    RoleAddResponseModel getRoleById(UUID id);

    void assignUserToRole(int userId, UUID roleId);
    List<Users> getUsersByRoleId(UUID roleId);



}
