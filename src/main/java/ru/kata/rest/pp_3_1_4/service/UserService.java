package ru.kata.rest.pp_3_1_4.service;



import ru.kata.rest.pp_3_1_4.entity.Role;
import ru.kata.rest.pp_3_1_4.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAll();
    void save(User user);
    User getById(long id);
    void removeById(long id);

    List<Role> listRoles();
    User loadUserByUsername(String username);
}
