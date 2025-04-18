package com.selim.taskmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users_role", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"users_id", "role_id"})
})
public class UsersRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(name = "users_id", nullable = false)
    private Integer usersId;


    @Column(name = "role_id", nullable = false)
    private UUID roleId;

    public UsersRoles(Integer usersId, UUID roleId) {
        this.usersId = usersId;
        this.roleId = roleId;
    }

    public UsersRoles() {}

    public UUID getRoleId() {
        return id;
    }
    public void setRoleId(UUID id) {
        this.id = id;
    }
    public Integer getUsersId() {
        return usersId;
    }
    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }


}
