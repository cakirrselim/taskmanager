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
    public List<UsersAddResponseModel> getAllUsers() {
        List<Users> users = usersDao.getAllUsers();
        return users.stream().map(u -> new UsersAddResponseModel(u.getId(), u.getName(), u.getSurname(), u.getUsername(), u.getPassword(), u.getMail())).toList();
    }

    @Override
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
    public void deleteUser(int id) {
        usersDao.deleteUser(id);
    }

    @Override
    public UsersAddResponseModel findByUsername(String username) {

        Users user = usersDao.findByUsername(username);
        UsersAddResponseModel usersAddResponseModel = new UsersAddResponseModel(
                user.getId(), user.getName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getMail());
        return usersAddResponseModel;
    }

    @Override
    public UsersAddResponseModel findByEmail(String email) {
        Users user = usersDao.findByEmail(email);
        UsersAddResponseModel usersAddResponseModel = new UsersAddResponseModel(
                user.getId(), user.getName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getMail());
        return usersAddResponseModel;
    }
}
