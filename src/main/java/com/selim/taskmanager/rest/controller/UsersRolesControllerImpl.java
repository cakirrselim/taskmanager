package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.GetRolesByUserIdModel;
import com.selim.taskmanager.service.UsersRolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roleUserRelation")
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
    public List<GetRolesByUserIdModel> getRolesByUserId(int userId) {
        return usersRolesService.getRolesByUserId(userId);
    }

    @Override
    public ResponseEntity<String> deleteUserFromRole(int userId, UUID roleId) {
        usersRolesService.deleteUserFromRole(userId, roleId);
        return ResponseEntity.ok("Role deleted");
    }
}
