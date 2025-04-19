package com.selim.taskmanager.rest.model;

public record GetUsersByUserIdModel(int id, String name, String surname, String username, String password, String email) {
}
