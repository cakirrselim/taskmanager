package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.GetUsersByUserIdModel;
import com.selim.taskmanager.rest.model.UsersAddRequestModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;
import com.selim.taskmanager.rest.model.UsersShowResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

public interface UsersController {
    @PostMapping("/add")
    ResponseEntity<UsersAddResponseModel> addUser(@RequestBody UsersAddRequestModel usersAddRequestModel);

    @GetMapping("/show")
    ResponseEntity<List<UsersShowResponseModel>> getUsers();

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteUser(@PathVariable int id);

    @PostMapping("/update")
    ResponseEntity<String> updateUser(@RequestBody UsersAddRequestModel usersAddRequestModel);

    @GetMapping("/showUsername/{username}")
    ResponseEntity<UsersAddResponseModel> findByUsername(@PathVariable String username);

    @GetMapping("/showEmail/{email}")
    ResponseEntity<UsersAddResponseModel> findByEmail(@PathVariable String email);

    @GetMapping("/{roleId}/users")
    ResponseEntity<List<GetUsersByUserIdModel>> getUsersByRoleId(@PathVariable UUID roleId);

}
