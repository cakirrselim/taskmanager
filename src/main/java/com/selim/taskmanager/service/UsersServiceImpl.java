package com.selim.taskmanager.service;

import com.selim.taskmanager.data.RoleDao;
import com.selim.taskmanager.data.UsersDao;
import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.rest.model.UsersAddRequestModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;
import com.selim.taskmanager.rest.model.UsersShowResponseModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersDao usersDao;
    private final RoleDao roleDao;

    public UsersServiceImpl(UsersDao usersDao, RoleDao roleDao) {
        this.usersDao = usersDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<Users> getUsersByRoleId(UUID roleId) {
        return usersDao.getUsersByRoleId(roleId);
    }


    @Override
    public List<UsersShowResponseModel> getAllUsers() {
        List<Users> users = usersDao.getAllUsers();
        return users.stream().map(u -> new UsersShowResponseModel(u.getId(), u.getName(), u.getSurname(), u.getUsername(), u.getPassword(), u.getMail(), u.getRoles())).toList();
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
                savedUser.getId(), savedUser.getName(), savedUser.getSurname(), savedUser.getUsername(), savedUser.getPassword(), savedUser.getMail());

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

    @Override
    public List<Role> getRolesByUserId(int userId) {
        return usersDao.getRolesByUserId(userId);
    }
}
