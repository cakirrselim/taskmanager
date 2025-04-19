package com.selim.taskmanager.data;

import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.entity.Users;
import java.util.List;
import java.util.UUID;

public interface RoleDao {
    Role addRole(Role role);
    void deleteRole(UUID roleId);
    void updateRole(Role role);
    Role getRoleByName(String roleName);
    Role getRoleById(UUID id);
    List<Role> getAllRoles();

    void assignUserToRole(int userId, UUID roleId);
    List<Users> getUsersByRoleId(UUID roleId);

}
