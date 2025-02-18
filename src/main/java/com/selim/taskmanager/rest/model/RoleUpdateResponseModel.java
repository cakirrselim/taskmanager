package com.selim.taskmanager.rest.model;

import java.util.UUID;

public record RoleUpdateResponseModel(UUID id, String name, String description) {
}
