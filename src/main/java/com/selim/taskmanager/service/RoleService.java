package com.selim.taskmanager.service;

import com.selim.taskmanager.rest.model.*;
import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleAddResponseModel addRole(RoleAddRequestModel roleAddRequestModel);
    void deleteRole(UUID roleId);
    void updateRole(RoleUpdateRequestModel roleUpdateRequestModel);
    RoleAddResponseModel getRoleByName(String roleName);
    RoleAddResponseModel getRoleById(UUID id);
    List<RoleShowResponseModel2> getAllRoles();

    List<GetRolesByUserIdModel> getRolesByUserId(int userId);




}
