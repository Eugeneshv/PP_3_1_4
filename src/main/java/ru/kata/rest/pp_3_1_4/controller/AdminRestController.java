package ru.kata.rest.pp_3_1_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import ru.kata.rest.pp_3_1_4.dto.UserDTO;
import ru.kata.rest.pp_3_1_4.entity.Role;
import ru.kata.rest.pp_3_1_4.entity.User;
import ru.kata.rest.pp_3_1_4.service.UserService;
import ru.kata.rest.pp_3_1_4.utils.MapperUser;


import java.util.List;


@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final MapperUser mapperUser;

    @Autowired
    public AdminRestController(UserService userService, MapperUser mapperUser) {
        this.userService = userService;

        this.mapperUser = mapperUser;
    }


    @GetMapping("/getroles")
    private List<Role> allRoles() {
        return userService.listRoles();
    }


    @GetMapping()
    private ResponseEntity<List<User>> allUsers() {
        final List<User> users = userService.getAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        return mapperUser.toUserDTO(user);
    }


    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.loadUserByUsername(user.getUsername()) == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping()
    public void updateUser(@RequestBody User user) {
        userService.save(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> DeleteModal(@PathVariable("id") long id) {

        userService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
