package ru.kata.rest.pp_3_1_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.rest.pp_3_1_4.dao.UserDao;
import ru.kata.rest.pp_3_1_4.entity.Role;
import ru.kata.rest.pp_3_1_4.entity.User;
import ru.kata.rest.pp_3_1_4.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleRepository roleRepository) {
        this.userDao = userDao;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userDao.getAll().forEach(user -> users.add(user));
        return users;
    }

    @Override
    public void save(User user) { userDao.save(user);
    }

    @Override
    public void removeById(long id) {
        userDao.removeById(id);
    }

    @Override
    public List<Role> listRoles() { return userDao.listRoles(); }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(username);
    }

}