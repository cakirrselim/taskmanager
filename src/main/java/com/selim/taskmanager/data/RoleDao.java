package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Role;
import java.util.List;
import java.util.UUID;

public interface RoleDao {
    Role addRole(Role role);
    void deleteRole(UUID roleId);
    void updateRole(Role role);
    Role getRoleByName(String roleName);
    List<Role> getRolesByUsername(String userName);
    Role getRoleById(UUID id);

    List<Role> getAllRoles();
    List<Role> getRolesByUserId(int userId);
}
