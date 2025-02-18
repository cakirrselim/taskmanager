package com.selim.taskmanager.rest.model;

import java.util.UUID;

public record RoleUpdateRequestModel(UUID id, String name, String description) {
}
