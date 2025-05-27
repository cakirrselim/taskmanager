package com.selim.taskmanager.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/auth")
public interface AuthController {

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody Map<String, String> loginData);
}