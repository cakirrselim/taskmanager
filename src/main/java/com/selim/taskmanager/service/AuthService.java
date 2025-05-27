package com.selim.taskmanager.service;

import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface AuthService {
    ResponseEntity<?> authenticateUser(Map<String, String> loginData);
}