package ru.kata.rest.pp_3_1_4.dao;


import ru.kata.rest.pp_3_1_4.entity.Role;
import ru.kata.rest.pp_3_1_4.entity.User;

import java.util.List;

public interface UserDao{

    List<User> getAll();
    void save(User user);
    User getById(long id);
    void removeById(long id);
    List<Role> listRoles();
    User loadUserByUsername(String username);
}
