package com.selim.taskmanager.rest.model;

import java.util.UUID;

public record RoleAddRequestModel(UUID id, String name, String description) {
}
