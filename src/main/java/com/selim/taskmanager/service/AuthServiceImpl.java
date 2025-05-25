
package com.selim.taskmanager.service;

import com.selim.taskmanager.data.UsersDao;
import com.selim.taskmanager.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public ResponseEntity<?> authenticateUser(Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        try {
            Users user = usersDao.authenticateUser(username, password);

            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Geçersiz kullanıcı adı veya şifre"));
        }
    }
}
