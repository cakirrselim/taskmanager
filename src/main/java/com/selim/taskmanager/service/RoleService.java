package com.selim.taskmanager.service;

import com.selim.taskmanager.rest.model.RoleAddRequestModel;
import com.selim.taskmanager.rest.model.RoleAddResponseModel;
import com.selim.taskmanager.rest.model.RoleShowResponseModel;
import com.selim.taskmanager.rest.model.RoleUpdateRequestModel;
import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleAddResponseModel addRole(RoleAddRequestModel roleAddRequestModel);
    void deleteRole(UUID roleId);
    void updateRole(RoleUpdateRequestModel roleUpdateRequestModel);
    List<RoleShowResponseModel> getAllRoles();
    RoleAddResponseModel getRoleByName(String roleName);
    RoleAddResponseModel getRoleById(UUID id);

}
