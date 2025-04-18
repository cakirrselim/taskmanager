package com.selim.taskmanager.rest.model;

public class RoleShowResponseModel {
    private String name;
    private String description;

    public RoleShowResponseModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
