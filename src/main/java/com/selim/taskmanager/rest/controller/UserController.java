package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        Users loggedUser = userService.login(user.getUsername(), user.getPassword());
        if (loggedUser != null) {
            loggedUser.setPassword(null);
            return ResponseEntity.ok(loggedUser);
        } else {

            return ResponseEntity.status(401).body("Geçersiz kullanıcı adı veya şifre");
        }
    }
}

