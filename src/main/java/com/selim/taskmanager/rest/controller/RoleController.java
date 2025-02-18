package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.*;
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
    ResponseEntity<String> updateRole(@RequestBody RoleUpdateRequestModel roleUpdateRequestModel);

    @GetMapping("/show")  // OK
    ResponseEntity<List<RoleShowResponseModel>> showRole();

    @GetMapping("/showByName/{name}") // OK
    ResponseEntity<RoleAddResponseModel> getRoleByName(@PathVariable String name);

    @GetMapping("/showById/{id}") // OK
    ResponseEntity<RoleAddResponseModel> getRoleById(@PathVariable UUID id);

}
