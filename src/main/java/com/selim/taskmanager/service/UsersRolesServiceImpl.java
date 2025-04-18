package com.selim.taskmanager.service;

import com.selim.taskmanager.data.UsersRolesDao;
import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.rest.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersRolesServiceImpl implements UsersRolesService {

    private final UsersRolesDao usersRolesDao;

    public UsersRolesServiceImpl(UsersRolesDao usersRolesDao) {
        this.usersRolesDao = usersRolesDao;
    }

    @Override
    public void assignRoleToUser(int userId, UUID roleId) {
        usersRolesDao.assignRoleToUser(userId, roleId);
    }

    @Override
    public List<RoleShowResponseModel> getRolesByUserId(int userId) {
        List<Role> roles = usersRolesDao.getRolesByUserId(userId);
        return roles.stream()
                .map(r -> new RoleShowResponseModel(r.getName(), r.getDescription()))
                .collect(Collectors.toList());
    }
}
