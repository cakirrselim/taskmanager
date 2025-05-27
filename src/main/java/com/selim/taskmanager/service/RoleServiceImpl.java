package com.selim.taskmanager.service;

import com.selim.taskmanager.data.RoleDao;
import com.selim.taskmanager.data.UsersDao;
import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.rest.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;
    private UsersDao usersDao;

    public RoleServiceImpl(RoleDao roleDao, UsersDao usersDao) {
        this.roleDao = roleDao;
        this.usersDao = usersDao;
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
    public List<RoleShowResponseModel> getAllRoles() {
        List<Role> roles = roleDao.getAllRoles();
        for (Role role : roles) {
            List<Users> users = usersDao.getUsersByRoleId(role.getId());
            role.setUsers(users);
        }
        return roles.stream().map(u -> new RoleShowResponseModel(u.getId(), u.getName(), u.getDescription(), u.getUsers())).collect(Collectors.toList());
    }

    @Override
    public RoleAddResponseModel getRoleByName(String roleName) {
        Role role = roleDao.getRoleByName(roleName);
        RoleAddResponseModel roleAddResponseModel = new RoleAddResponseModel(role.getId(), role.getName(), role.getDescription());
        return roleAddResponseModel;
    }

    @Override
    public List<GetRolesByUserIdModel> getRolesByUsername(String username) {
        return roleDao.getRolesByUsername(username).stream().map(u -> new GetRolesByUserIdModel(
                u.getId(), u.getName(), u.getDescription())).collect(Collectors.toList());
    }


    @Override
    public RoleAddResponseModel getRoleById(UUID id) {
        Role role = roleDao.getRoleById(id);
        RoleAddResponseModel roleAddResponseModel = new RoleAddResponseModel(
                id, roleDao.getRoleById(id).getName(), roleDao.getRoleById(id).getDescription());
        return roleAddResponseModel;
    }


    @Override
    public List<GetRolesByUserIdModel> getRolesByUserId(int userId) {
        return roleDao.getRolesByUserId(userId).stream().map(role -> new GetRolesByUserIdModel(
                        role.getId(),
                        role.getName(),
                        role.getDescription()
                )).collect(Collectors.toList());
    }
}
