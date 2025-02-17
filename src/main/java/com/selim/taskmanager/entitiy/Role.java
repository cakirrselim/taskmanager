package com.selim.taskmanager.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

public class Role {
    @Id
    @GeneratedValue(generator = "uuid-generator", strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Role() {
        this.id = UUID.randomUUID();
    }

    public Role(UUID id, String name,  String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
