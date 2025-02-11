package com.selim.taskmanager.service;

import com.selim.taskmanager.entitiy.Role;
import com.selim.taskmanager.rest.model.RoleAddRequestModel;
import com.selim.taskmanager.rest.model.RoleAddResponseModel;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleAddResponseModel addRole(RoleAddRequestModel roleAddRequestModel);
    void deleteRole(UUID roleId);
    void updateRole(RoleAddRequestModel roleAddRequestModel);
    void updateDescription(RoleAddRequestModel roleAddRequestModel);
    void deleteDescription(UUID roleId);
    List<RoleAddResponseModel> getAllRoles();
    RoleAddResponseModel getRoleByName(String roleName);
    RoleAddResponseModel getRoleById(UUID id);

}
