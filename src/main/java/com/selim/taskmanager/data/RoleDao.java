package com.selim.taskmanager.data;

import com.selim.taskmanager.entitiy.Role;
import java.util.List;
import java.util.UUID;

public interface RoleDao {
    Role addRole(Role role);
    void deleteRole(UUID roleId);
    void updateRole(Role role);
    List<Role> getAllRoles();
    Role getRoleByName(String roleName);
    Role getRoleById(UUID id);
}
