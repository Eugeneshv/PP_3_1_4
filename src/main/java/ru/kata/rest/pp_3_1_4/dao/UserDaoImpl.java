package ru.kata.rest.pp_3_1_4.dao;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.rest.pp_3_1_4.entity.Role;
import ru.kata.rest.pp_3_1_4.entity.User;
import ru.kata.rest.pp_3_1_4.repository.RoleRepository;
import ru.kata.rest.pp_3_1_4.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserDaoImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        roleRepository.save(new Role(1L,"ROLE_ADMIN"));
        roleRepository.save(new Role(2L,"ROLE_USER"));
        Set<Role> roleOne = new HashSet<>();
        roleOne.add(roleRepository.getReferenceById(1L));
        roleOne.add(roleRepository.getReferenceById(2L));
        Set<Role> roleTwo = new HashSet<>();
        roleTwo.add(roleRepository.getReferenceById(2L));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodeAdmin = passwordEncoder.encode("admin");
        String encodeUser = passwordEncoder.encode("user");
        userRepository.save(new User("admin","admin",35,"admin@mail.ru",encodeAdmin, roleOne));
        userRepository.save(new User("user","user",30,"user@mail.ru",encodeUser, roleTwo));


    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
       /* if (user.getPassword() != null) {
            if (!user.getPassword().startsWith("{noop}")) {
                user.setPassword("{noop}" + user.getPassword());
                userRepository.save(user);
            }
        }*/
    }

    @Override
    public User getById(long id) {
        return userRepository.getReferenceById(id);
    }
    @Override
    public void removeById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByUsername(username);
        return user;
    }
}
