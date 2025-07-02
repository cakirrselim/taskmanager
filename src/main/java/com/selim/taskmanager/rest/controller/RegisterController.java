package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.UsersAddRequestModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/register")
public interface RegisterController {

    ResponseEntity<UsersAddResponseModel> register(@RequestBody Map<String, String> registerData);

}