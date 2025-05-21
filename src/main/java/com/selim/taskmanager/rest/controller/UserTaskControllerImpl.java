package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.rest.model.TaskShowResponseModel;
import com.selim.taskmanager.service.UserTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taskUserRelation")
public class UserTaskControllerImpl implements UserTaskController {

    private final UserTaskService userTaskService;

    public UserTaskControllerImpl(UserTaskService userTaskService) {
        this.userTaskService = userTaskService;
    }

    @Override
    public ResponseEntity<String> assignTaskToUser(int userId, int taskId) {
        userTaskService.assignRoleToUser(userId, taskId);
        return ResponseEntity.ok("Assigned task to user");
    }
    @Override
    public ResponseEntity<String> deleteUserFromRole(int userId, int taskId) {
        userTaskService.deleteUserFromTask(taskId, userId);
        return ResponseEntity.ok("Deleted user from role");
    }

    @Override
    public List<TaskShowResponseModel> getRolesByUserId(int userId) {
        return userTaskService.getTasksByUserId(userId);
    }


}
