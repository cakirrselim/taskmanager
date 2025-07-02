package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.UsersAddRequestModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;
import com.selim.taskmanager.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterControllerImpl implements RegisterController {

    private final UsersService usersService;

    @Autowired
    public RegisterControllerImpl(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    @PostMapping
    public ResponseEntity<UsersAddResponseModel> register(@RequestBody Map<String, String> registerData) {
        // Eksik alan kontrolü eklenebilir
        UsersAddRequestModel reqModel = new UsersAddRequestModel(
                0,
                registerData.getOrDefault("name", ""),
                registerData.getOrDefault("surname", ""),
                registerData.getOrDefault("username", ""),
                registerData.getOrDefault("password", ""),
                registerData.getOrDefault("email", "")
        );
        UsersAddResponseModel response = usersService.addUser(reqModel);
        return ResponseEntity.ok(response);
    }
}