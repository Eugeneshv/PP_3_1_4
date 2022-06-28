package ru.kata.rest.pp_3_1_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.rest.pp_3_1_4.dto.UserDTO;
import ru.kata.rest.pp_3_1_4.entity.User;
import ru.kata.rest.pp_3_1_4.service.UserService;
import ru.kata.rest.pp_3_1_4.utils.MapperUser;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final MapperUser mapperUser;

    @Autowired
    public UserController(UserService userService, MapperUser mapperUser) {
        this.userService = userService;
        this.mapperUser = mapperUser;
    }



    @GetMapping("/user")
    public User viewUser (@AuthenticationPrincipal User user) {

        return user;
    }

}
