package ru.kata.rest.pp_3_1_4.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.rest.pp_3_1_4.entity.User;


@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user")
    public User viewUser (@AuthenticationPrincipal User user) {
        return user;
    }
}
