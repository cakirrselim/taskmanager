package com.selim.taskmanager.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users_task", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "task_id"})})
public class UsersTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private Integer usersId;

    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    public UsersTasks() {}

    public UsersTasks(Integer usersId, Integer taskId) {
        this.usersId = usersId;
        this.taskId = taskId;
    }
    public Integer getUsersId() {
        return usersId;
    }
    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }
    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

}
