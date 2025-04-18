package com.selim.taskmanager.service;

import com.selim.taskmanager.data.RoleDao;
import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.rest.model.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(@Lazy RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public RoleAddResponseModel addRole(RoleAddRequestModel roleAddRequestModel) {
        Role role = new Role();
        role.setName(roleAddRequestModel.name());
        role.setDescription(roleAddRequestModel.description());
        Role savedRole = roleDao.addRole(role);

        RoleAddResponseModel roleAddResponseModel = new RoleAddResponseModel(
                savedRole.getId(), savedRole.getName(), savedRole.getDescription());
        return roleAddResponseModel;
    }

    @Override
    public void deleteRole(UUID roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public void updateRole(RoleUpdateRequestModel roleUpdateRequestModel) {
        Role role = new Role();
        role.setId(roleUpdateRequestModel.id());
        role.setName(roleUpdateRequestModel.name());
        role.setDescription(roleUpdateRequestModel.description());
        roleDao.updateRole(role);
    }

    @Override
    public List<RoleShowResponseModel2> getAllRoles() {
        Role role = new Role();
        List<Role> roles = roleDao.getAllRoles();
        return roles.stream().map(u -> new RoleShowResponseModel2(u.getId(), u.getName(), u.getDescription())).collect(Collectors.toList());
    }


    @Override
    public RoleAddResponseModel getRoleByName(String roleName) {
        Role role = roleDao.getRoleByName(roleName);
        RoleAddResponseModel roleAddResponseModel = new RoleAddResponseModel(role.getId(), role.getName(), role.getDescription());
        return roleAddResponseModel;
    }

    @Override
    public RoleAddResponseModel getRoleById(UUID id) {
        Role role = roleDao.getRoleById(id);
        RoleAddResponseModel roleAddResponseModel = new RoleAddResponseModel(
                id, roleDao.getRoleById(id).getName(), roleDao.getRoleById(id).getDescription());
        return roleAddResponseModel;
    }

    @Override
    public void assignUserToRole(int userId, UUID roleId) {
        roleDao.assignUserToRole(userId, roleId);
    }
    @Override
    public List<Users> getUsersByRoleId(UUID roleId) {
        return roleDao.getUsersByRoleId(roleId);
    }
}
