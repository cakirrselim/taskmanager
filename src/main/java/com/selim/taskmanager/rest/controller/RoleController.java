package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.RoleAddRequestModel;
import com.selim.taskmanager.rest.model.RoleAddResponseModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface RoleController {

    @PostMapping("/add")  //
    ResponseEntity<RoleAddResponseModel> addRole(@RequestBody RoleAddRequestModel roleAddRequestModel);

    @DeleteMapping("/deleteRole/{id}")  // OK
    ResponseEntity<String> deleteRole(@PathVariable UUID id);

    @PostMapping("/updateRole") // -
    ResponseEntity<String> updateRole(@RequestBody RoleAddRequestModel roleAddRequestModel);

    @GetMapping("/show")  // OK
    ResponseEntity<List<RoleAddResponseModel>> showRole();

    @GetMapping("/showByName/{name}") // OK
    ResponseEntity<RoleAddResponseModel> getRoleByName(@PathVariable String name);

    @GetMapping("/showById/{id}") // OK
    ResponseEntity<RoleAddResponseModel> getRoleById(@PathVariable UUID id);

}
