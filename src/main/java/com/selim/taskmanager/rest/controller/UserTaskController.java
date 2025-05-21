package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.TaskShowResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

public interface UserTaskController {

    @PostMapping("/{userId}/assignTask/{taskId}")
    ResponseEntity<String> assignTaskToUser(@PathVariable int userId, @PathVariable int taskId);

    @GetMapping("/getTasksByUserId/{userId}")
    List<TaskShowResponseModel> getRolesByUserId(@PathVariable int userId);

    @DeleteMapping("/{userId}/deleteTask/{taskId}")
    ResponseEntity<String> deleteUserFromRole(@PathVariable int userId, @PathVariable int taskId);
}
