package com.selim.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Users {

    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String mail;
    @JsonInclude(JsonInclude.Include.NON_NULL) // THIS ANNOTATION PROVIDES THAT IF rules = null, IT WON'T PRINT.
    private List<Role> roles;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Task> tasks;

    public Users() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


}
