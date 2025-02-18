package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.RoleAddRequestModel;
import com.selim.taskmanager.rest.model.RoleAddResponseModel;
import com.selim.taskmanager.rest.model.RoleShowResponseModel;
import com.selim.taskmanager.rest.model.RoleUpdateRequestModel;
import com.selim.taskmanager.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;
    public RoleControllerImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<RoleAddResponseModel> addRole(RoleAddRequestModel roleAddRequestModel) {
        RoleAddResponseModel roleAddResponseModel = roleService.addRole(roleAddRequestModel);
        return ResponseEntity.ok(roleAddResponseModel);
    }

    @Override
    public ResponseEntity<String> deleteRole(UUID id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role with id \" + id + \" has been deleted");
    }

    @Override
    public ResponseEntity<String> updateRole(RoleUpdateRequestModel roleUpdateRequestModel) {
        roleService.updateRole(roleUpdateRequestModel);
        return ResponseEntity.ok("UPDATED");
    }

    @Override
    public ResponseEntity<List<RoleShowResponseModel>> showRole() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @Override
    public ResponseEntity<RoleAddResponseModel> getRoleByName(String name) {
        RoleAddResponseModel roleAddResponseModel = roleService.getRoleByName(name);
        return ResponseEntity.ok(roleAddResponseModel);
    }

    @Override
    public ResponseEntity<RoleAddResponseModel> getRoleById(UUID id) {
        RoleAddResponseModel roleAddResponseModel = roleService.getRoleById(id);
        return ResponseEntity.ok(roleAddResponseModel);
    }
}
