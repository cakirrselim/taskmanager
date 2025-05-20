package com.selim.taskmanager.service;

import com.selim.taskmanager.data.UserRepository;
import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.rest.model.LoginModel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // Constructor injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Users login(String username, String password) {
        try {
            return userRepository.findByUsernameAndPassword(username, password);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
