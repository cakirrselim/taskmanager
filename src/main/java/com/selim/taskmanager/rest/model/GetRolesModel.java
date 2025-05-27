package com.selim.taskmanager.rest.model;

import java.util.UUID;

public record GetRolesModel(UUID id, String name, String description) {
}
