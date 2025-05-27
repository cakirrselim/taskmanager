package com.selim.taskmanager.rest.model;

import java.util.UUID;

public record GetRolesByUserIdModel(UUID id, String name, String description) {
}
