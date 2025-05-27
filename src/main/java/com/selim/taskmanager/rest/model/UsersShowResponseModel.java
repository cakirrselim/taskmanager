package com.selim.taskmanager.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsersShowResponseModel(
        int id, String name, String surname, String username, String password, String email, List roles, List tasks) {
}
