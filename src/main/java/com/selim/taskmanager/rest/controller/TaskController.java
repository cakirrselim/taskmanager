package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.TaskAddRequestModel;
import com.selim.taskmanager.rest.model.TaskAddResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface TaskController {

    @PostMapping("/add")
    ResponseEntity<TaskAddResponseModel> createTask(@RequestBody TaskAddRequestModel taskAddRequestModel);

    @GetMapping("/show")
    ResponseEntity<List<TaskAddResponseModel>> getAll();

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteTask(@PathVariable int id);

    @PostMapping("/update")
    ResponseEntity<String> updateTask(@RequestBody TaskAddRequestModel taskAddRequestModel);

    @GetMapping("show/{id}")
    ResponseEntity<TaskAddResponseModel> getTaskById(@PathVariable int id);

}
