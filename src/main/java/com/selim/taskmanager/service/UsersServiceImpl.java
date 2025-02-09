package com.selim.taskmanager.service;

import com.selim.taskmanager.data.UsersDao;
import com.selim.taskmanager.entitiy.Users;
import com.selim.taskmanager.rest.model.UsersAddRequestModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersDao usersDao;

    public UsersServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    @Override
    @Transactional
    public List<UsersAddResponseModel> getAllUsers() {
        List<Users> users = usersDao.getAllUsers();
        return users.stream().map(u -> new UsersAddResponseModel(u.getId(), u.getName(), u.getSurname(), u.getUsername(), u.getPassword(), u.getMail())).toList();
    }

    @Override
    @Transactional
    public UsersAddResponseModel addUser(UsersAddRequestModel usersAddRequestModel) {
        Users users = new Users();
        users.setName(usersAddRequestModel.name());
        users.setSurname(usersAddRequestModel.surname());
        users.setUsername(usersAddRequestModel.username());
        users.setPassword(usersAddRequestModel.password());
        users.setMail(usersAddRequestModel.email());
        Users savedUser = usersDao.addUser(users);
        UsersAddResponseModel usersAddResponseModel = new UsersAddResponseModel(
                savedUser.getId(), savedUser.getName(), savedUser.getSurname(), savedUser.getUsername(), savedUser.getPassword(), savedUser.getMail() );

        return usersAddResponseModel;
    }

    @Override
    @Transactional
    public void updateUser(UsersAddRequestModel usersAddRequestModel) {
        Users users = new Users();
        users.setId(usersAddRequestModel.id());
        users.setName(usersAddRequestModel.name());
        users.setSurname(usersAddRequestModel.surname());
        users.setUsername(usersAddRequestModel.username());
        users.setPassword(usersAddRequestModel.password());
        users.setMail(usersAddRequestModel.email());
        usersDao.updateUser(users);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        usersDao.deleteUser(id);
    }

    @Override
    @Transactional
    public UsersAddResponseModel findByUsername(String username) {
        usersDao.findByUsername(username);
        UsersAddResponseModel usersAddResponseModel = new UsersAddResponseModel(
                usersDao.findByUsername(username).getId(), usersDao.findByUsername(username).getName(), usersDao.findByUsername(username).getSurname(),
                usersDao.findByUsername(username).getUsername(), usersDao.findByUsername(username).getPassword(),
                usersDao.findByUsername(username).getMail());
        return usersAddResponseModel;
    }

    @Override
    @Transactional
    public UsersAddResponseModel findByEmail(String email) {
        usersDao.findByEmail(email);
        UsersAddResponseModel usersAddResponseModel = new UsersAddResponseModel(
                usersDao.findByEmail(email).getId(), usersDao.findByUsername(email).getName(),
                usersDao.findByEmail(email).getSurname(), usersDao.findByUsername(email).getUsername(),
                usersDao.findByUsername(email).getPassword(), usersDao.findByUsername(email).getMail());
        return null;
    }
}
