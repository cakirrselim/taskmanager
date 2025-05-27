package com.selim.taskmanager.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoleShowResponseModel(UUID id, String name, String description, List users) {
}
