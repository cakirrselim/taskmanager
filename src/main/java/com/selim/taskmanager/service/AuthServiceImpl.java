
package com.selim.taskmanager.service;

import com.selim.taskmanager.data.UsersDao;
import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private RoleService roleService;

    @Override
    public ResponseEntity<?> authenticateUser(Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        try {
            Users user = usersDao.authenticateUser(username, password);

            // Kullanıcının rollerini al
            List<String> roles = roleService.getRolesByUsername(username)
                    .stream()
                    .map(role -> role.name())
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            response.put("roles", roles); // Rolleri yanıta ekle

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Geçersiz kullanıcı adı veya şifre"));
        }
    }
}