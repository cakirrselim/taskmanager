package com.selim.taskmanager.service;

import com.selim.taskmanager.data.UsersDao;
import com.selim.taskmanager.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Ekle
import org.springframework.security.crypto.password.PasswordEncoder; // Ekle

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private RoleService roleService;

    // PasswordEncoder enjekte edilebilir
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<?> authenticateUser(Map<String, String> loginData) {
        String username = loginData.get("username");
        String rawPassword = loginData.get("password");

        try {
            Users user = usersDao.findByUsername(username); // Sadece username ile getir
            if (user == null) {
                throw new Exception("Kullanıcı bulunamadı");
            }
            String hashedPassword = user.getPassword(); // Veritabanındaki hash

            // Şifreyi kontrol et
            if (!passwordEncoder.matches(rawPassword, hashedPassword)) {
                throw new Exception("Şifre hatalı");
            }

            List<String> roles = roleService.getRolesByUsername(username)
                    .stream()
                    .map(role -> role.name())
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("username", user.getUsername());
            response.put("roles", roles);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Geçersiz kullanıcı adı veya şifre"));
        }
    }
}