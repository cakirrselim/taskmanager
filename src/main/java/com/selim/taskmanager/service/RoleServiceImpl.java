package com.selim.taskmanager.service;

import com.selim.taskmanager.data.RoleDao;
import com.selim.taskmanager.entitiy.Role;
import com.selim.taskmanager.rest.model.RoleAddRequestModel;
import com.selim.taskmanager.rest.model.RoleAddResponseModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public RoleAddResponseModel addRole(RoleAddRequestModel roleAddRequestModel) {
        Role role = new Role();
        role.setId(roleAddRequestModel.id());
        //role.setId(UUID.randomUUID());
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
    public void updateRole(RoleAddRequestModel roleAddRequestModel) {
        Role role = new Role();
        role.setId(roleAddRequestModel.id());
        role.setName(roleAddRequestModel.name());
        role.setDescription(roleAddRequestModel.description());
        roleDao.updateRole(role);
    }

    @Override
    public List<RoleAddResponseModel> getAllRoles() {
        List<Role> roles = roleDao.getAllRoles();
        return roles.stream().map(r -> new RoleAddResponseModel(r.getId(), r.getName(), r.getDescription())).toList();
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
}
