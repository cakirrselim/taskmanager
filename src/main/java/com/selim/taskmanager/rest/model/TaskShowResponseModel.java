package com.selim.taskmanager.rest.model;

public class TaskShowResponseModel {
    private Integer id;
    private String name;
    public TaskShowResponseModel(int id, String name) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public Integer getId() {
        return id;
    }
}
