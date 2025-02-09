package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.UsersAddRequestModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;
import com.selim.taskmanager.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersControllerImpl implements UsersController {

    private final UsersService usersService;

    public UsersControllerImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public ResponseEntity<UsersAddResponseModel> addUser(UsersAddRequestModel usersAddRequestModel) {
        UsersAddResponseModel usersAddResponseModel = usersService.addUser(usersAddRequestModel);
        return ResponseEntity.ok(usersAddResponseModel);
    }

    @Override
    public ResponseEntity<List<UsersAddResponseModel>> getUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @Override
    public ResponseEntity<String> deleteUser(int id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok("User deleted" + id);
    }

    @Override
    public ResponseEntity<String> updateUser(UsersAddRequestModel usersAddRequestModel) {
        usersService.updateUser(usersAddRequestModel);
        return ResponseEntity.ok("User updated");
    }

    @Override
    public ResponseEntity<UsersAddResponseModel> findByUsername(String username) {
        return ResponseEntity.ok(usersService.findByUsername(username));
    }

    @Override
    public ResponseEntity<UsersAddResponseModel> findByEmail(String email) {
        return ResponseEntity.ok(usersService.findByEmail(email));
    }
}
