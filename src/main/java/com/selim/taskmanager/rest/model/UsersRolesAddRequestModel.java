package com.selim.taskmanager.rest.model;

import java.util.UUID;

public record UsersRolesAddRequestModel(Integer userId, UUID roleId) {
}
