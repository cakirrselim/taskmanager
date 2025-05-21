package com.selim.taskmanager.rest.model;

import java.util.List;

public record UsersShowResponseModel(
        int id, String name, String surname, String username, String password, String email, List roles, List tasks) {
}
