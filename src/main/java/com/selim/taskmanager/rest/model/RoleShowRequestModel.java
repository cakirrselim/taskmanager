package com.selim.taskmanager.rest.model;

import java.util.UUID;

public record RoleShowRequestModel(UUID id, String name, String description) {
}
