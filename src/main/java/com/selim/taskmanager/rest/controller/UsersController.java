package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.UsersAddRequestModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UsersController {
    @PostMapping("/add")
    ResponseEntity<UsersAddResponseModel> addUser(@RequestBody UsersAddRequestModel usersAddRequestModel);

    @GetMapping("/show")
    ResponseEntity<List<UsersAddResponseModel>> getUsers();

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteUser(@PathVariable int id);

    @PostMapping("/update")
    ResponseEntity<String> updateUser(@RequestBody UsersAddRequestModel usersAddRequestModel);

    @GetMapping("/showUsername/{username}")
    ResponseEntity<UsersAddResponseModel> findByUsername(@PathVariable String username);

    @GetMapping("/showEmail/{mail}")
    ResponseEntity<UsersAddResponseModel> findByEmail(@PathVariable String mail);

}
