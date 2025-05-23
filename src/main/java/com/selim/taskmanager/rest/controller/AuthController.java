package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        try {
            // Kullanıcıyı veritabanından al
            Map<String, Object> user = jdbcTemplate.queryForMap(
                    "SELECT * FROM users WHERE username = ? AND password = ?", username, password);

            int userId = (Integer) user.get("id");

            // Rolleri getir
            List<String> roles = jdbcTemplate.query(
                    "SELECT r.name FROM role r " +
                            "JOIN users_role ur ON r.id = ur.role_id " +
                            "WHERE ur.users_id = ?",
                    new Object[]{userId},
                    (rs, rowNum) -> rs.getString("name")
            );

            // Cevap objesi oluştur
            Map<String, Object> response = new HashMap<>();
            response.put("id", userId);
            response.put("username", user.get("username"));
            response.put("roles", roles);

            return ResponseEntity.ok(response);

        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Geçersiz kullanıcı adı veya şifre"));
        }
    }
}


/*

 ÇALIŞIR ESKİ HALİ
package com.selim.taskmanager.rest.controller;

import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        Users loggedUser = userService.login(user.getUsername(), user.getPassword());
        if (loggedUser != null) {
            loggedUser.setPassword(null);
            return ResponseEntity.ok(loggedUser);
        } else {

            return ResponseEntity.status(401).body("Geçersiz kullanıcı adı veya şifre");
        }
    }
}


 */

