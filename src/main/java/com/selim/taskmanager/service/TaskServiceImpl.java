package com.selim.taskmanager.service;


import com.selim.taskmanager.data.TaskDao;
import com.selim.taskmanager.entity.Task;
import com.selim.taskmanager.rest.model.TaskAddRequestModel;
import com.selim.taskmanager.rest.model.TaskAddResponseModel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }


    @Override
    @Transactional
    public List<TaskAddResponseModel> getAll() {
        List<Task> all = taskDao.getAllTasks();
        return all.stream().map(t -> new TaskAddResponseModel(t.getId(), t.getName())).toList();
    }

    @Override
    @Transactional
    public TaskAddResponseModel add(TaskAddRequestModel task) {
        Task task1 = new Task();
        task1.setName(task.name());
        Task savedTask = taskDao.saveTask(task1);
        TaskAddResponseModel taskAddResponseModel = new TaskAddResponseModel(savedTask.getId(), savedTask.getName());
        return taskAddResponseModel;
    }

    @Override
    @Transactional
    public void delete(int id) {
        taskDao.deleteTask(id);
    }


    @Override
    @Transactional
    public void update(TaskAddRequestModel task) {
        Task task1 = new Task();
        task1.setId(task.id());
        task1.setName(task.name());
        taskDao.updateTask(task1);
    }


    @Override
    @Transactional
    public TaskAddResponseModel getById(int id) {
        taskDao.getTaskById(id);
        TaskAddResponseModel taskAddResponseModel = new TaskAddResponseModel(id, taskDao.getTaskById(id).getName());
        return taskAddResponseModel;
    }
}


