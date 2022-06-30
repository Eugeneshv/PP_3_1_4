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
@RequestMapping("/api")
public class AdminRestController {

    private final  UserService userService;
    private final MapperUser mapperUser;

    @Autowired
    public AdminRestController(UserService userService, MapperUser mapperUser) {
        this.userService = userService;

        this.mapperUser = mapperUser;
    }


    @GetMapping("/admin/getroles")
    private List<Role> allRoles() {
        return userService.listRoles();
    }


    @GetMapping("/admin")
    private ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
/*    @GetMapping("/admin")
    private List<User> allUsers() {
        return userService.getAll();
    }*/

    @GetMapping("/admin/{id}")
    public UserDTO getUser (@PathVariable("id") long id) {
        User user = userService.getById(id);
        return mapperUser.toUserDTO(user);
    }

/*    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }*/

/*
    @GetMapping("/admin/{id}")
    public User getUser (@PathVariable("id") long id) {
        return userService.getById(id);
    }
*/



    @PostMapping("/admin")
    public ResponseEntity<?> createUser(@RequestBody User user) {
            if (userService.loadUserByUsername(user.getUsername()) == null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.save(user);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/admin")
    public void updateUser(@RequestBody User user) {
/*        User checkUser = userService.loadUserByUsername(user.getUsername());
        if (checkUser.getId() == user.getId()) {*/
            userService.save(user);
        //}

    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<User>DeleteModal(@PathVariable("id") long id) {

        userService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
