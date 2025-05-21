package com.selim.taskmanager.service;

import com.selim.taskmanager.data.UserTaskDao;
import com.selim.taskmanager.entity.Task;
import com.selim.taskmanager.rest.model.TaskShowResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserTaskServiceImpl implements UserTaskService {

    private final UserTaskDao userTaskDao;

    @Autowired
    public UserTaskServiceImpl(UserTaskDao userTaskDao) {
        this.userTaskDao = userTaskDao;
    }

    @Override
    public void assignRoleToUser(int userId, int taskId) {
        userTaskDao.assignRoleToUser(userId, taskId);
    }

    @Override
    public void deleteUserFromTask(int userId, int taskId) {
        userTaskDao.deleteUserFromTask(userId, taskId);
    }

    @Override
    public List<TaskShowResponseModel> getTasksByUserId(int userId) {
        List<Task> tasks = userTaskDao.getTasksByUserId(userId);
        return tasks.stream().map(
                r-> new TaskShowResponseModel(r.getId(), r.getName())).collect(Collectors.toList());
    }
}
