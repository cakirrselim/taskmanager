package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.RoleShowResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.UUID;

public interface UsersRolesController {

    @PostMapping("/{userId}/assignRole/{roleId}")
    ResponseEntity<String> assignRoleToUser(@PathVariable int userId, @PathVariable UUID roleId);

    @GetMapping("/getRolesByUserId/{userId}")
    List<RoleShowResponseModel> getRolesByUserId(@PathVariable int userId);

    @DeleteMapping("/{userId}/deleteRole/{roleId}")
    ResponseEntity<String> deleteUserFromRole(@PathVariable int userId, @PathVariable UUID roleId);
}


