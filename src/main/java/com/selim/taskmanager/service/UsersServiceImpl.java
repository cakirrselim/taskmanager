package com.selim.taskmanager.service;

import com.selim.taskmanager.data.RoleDao;
import com.selim.taskmanager.data.TaskDao;
import com.selim.taskmanager.data.UsersDao;
import com.selim.taskmanager.data.UsersRolesDao;
import com.selim.taskmanager.entity.Role;
import com.selim.taskmanager.entity.Task;
import com.selim.taskmanager.entity.Users;
import com.selim.taskmanager.rest.model.GetUsersByUserIdModel;
import com.selim.taskmanager.rest.model.UsersAddRequestModel;
import com.selim.taskmanager.rest.model.UsersAddResponseModel;
import com.selim.taskmanager.rest.model.UsersShowResponseModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersDao usersDao;
    private final RoleDao roleDao;
    private final UsersRolesDao usersRolesDao;
    private final TaskDao taskDao;

    public UsersServiceImpl(UsersDao usersDao, RoleDao roleDao, UsersRolesDao usersRolesDao, TaskDao taskDao) {
        this.usersDao = usersDao;
        this.roleDao = roleDao;
        this.usersRolesDao = usersRolesDao;
        this.taskDao = taskDao;
    }

    // TİP DÖNÜŞÜMÜ
    @Override
    public List<GetUsersByUserIdModel> getUsersByRoleId(UUID roleId) {
        return usersDao.getUsersByRoleId(roleId).stream().map(users -> new GetUsersByUserIdModel(
                users.getId(), users.getName(), users.getSurname(), users.getUsername(), users.getPassword(), users.getMail()
        )).collect(Collectors.toList());
    }

    // EZİYET CODING
    @Override
    public List<UsersShowResponseModel> getAllUsers() {
        List<Users> users = usersDao.getAllUsers();
        for (Users user : users) {
            List<Role> roles = usersRolesDao.getRolesByUserId(user.getId());
            user.setRoles(roles);
            List<Task> tasks = taskDao.getTaskByUserId(user.getId());
            user.setTasks(tasks);
        }
        return users.stream().map(u -> new UsersShowResponseModel(u.getId(), u.getName(), u.getSurname(), u.getUsername(), u.getPassword(), u.getMail(), u.getRoles(), u.getTasks())).toList();
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
}
