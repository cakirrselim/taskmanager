package com.selim.taskmanager.rest.controller;


import com.selim.taskmanager.rest.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

public interface RoleController {

    @PostMapping("/add")
    ResponseEntity<RoleAddResponseModel> addRole(@RequestBody RoleAddRequestModel roleAddRequestModel);

    @DeleteMapping("/deleteRole/{id}")
    ResponseEntity<String> deleteRole(@PathVariable UUID id);

    @PostMapping("/updateRole")
    ResponseEntity<String> updateRole(@RequestBody RoleUpdateRequestModel roleUpdateRequestModel);

    @GetMapping("/show")
    ResponseEntity<List<RoleShowResponseModel>> showRole();

    @GetMapping("/showByName/{name}")
    ResponseEntity<RoleAddResponseModel> getRoleByName(@PathVariable String name);

    @GetMapping("/showByUserName/{name}")
    ResponseEntity<List<GetRolesByUserIdModel>> getRolesByUsername(@PathVariable String name);

    @GetMapping("/showById/{id}")
    ResponseEntity<RoleAddResponseModel> getRoleById(@PathVariable UUID id);

    @GetMapping("/{userId}/roles")
    ResponseEntity<List<GetRolesByUserIdModel>> getRolesByUserId(@PathVariable("userId") int userId);

}
