package com.selim.taskmanager.entity;

import java.util.List;

public class Users {
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String mail;

    private List<Role> roles;

    // users id ile users_role e gidip o id e ait role_id leri al
    // o role_id ler ile role tablosuna gidip o id deki name leri alıp users taki roles e setle

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

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }








}
