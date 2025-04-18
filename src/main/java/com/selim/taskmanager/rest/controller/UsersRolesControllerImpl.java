package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.data.UsersRolesDao;
import com.selim.taskmanager.rest.model.RoleShowResponseModel;
import com.selim.taskmanager.rest.model.UsersRolesAddRequestModel;
import com.selim.taskmanager.rest.model.UsersRolesAddResponseModel;
import com.selim.taskmanager.service.UsersRolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assignRoleToUser")
public class UsersRolesControllerImpl implements UsersRolesController {

    private final UsersRolesService usersRolesService;

    public UsersRolesControllerImpl(UsersRolesService usersRolesService) {
        this.usersRolesService = usersRolesService;
    }

    @Override
    public ResponseEntity<String> assignRoleToUser(int userId, UUID roleId) {
        usersRolesService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok("Role assigned");
    }

    @Override
    public List<RoleShowResponseModel> getRolesByUserId(int userId) {
        return usersRolesService.getRolesByUserId(userId);
    }
}
