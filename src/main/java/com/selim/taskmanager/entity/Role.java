package com.selim.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Role {

    @Id
    @GeneratedValue(generator = "uuid-generator", strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    // THIS ANNOTATION PROVIDES THAT IF rules = null, IT WON'T PRINT. I ASKED CHAT AND THIS IS WHAT CHAT GAVE ME.
    // I COULDN'T FIND THE SOLUTION WITH MODELS :D
    private List<Users> users;


    public Role() {
    }

    public Role(UUID id, String name, String description) {
    }


    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
