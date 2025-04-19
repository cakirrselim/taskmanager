package com.selim.taskmanager.rest.model;

import java.util.List;
import java.util.UUID;

public record RoleShowResponseModel2(UUID id, String name, String description, List users) {
}
