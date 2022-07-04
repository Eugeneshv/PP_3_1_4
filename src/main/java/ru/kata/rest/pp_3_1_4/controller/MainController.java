package ru.kata.rest.pp_3_1_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.rest.pp_3_1_4.entity.Role;
import ru.kata.rest.pp_3_1_4.entity.User;
import ru.kata.rest.pp_3_1_4.service.UserService;


import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String welcomePage() {
        return "/index";
    }


    @GetMapping("/admin")
    /*@PostMapping("/admin/new")*/
    public String getAllUser(Model model, Principal principal) {
        User userActive = userService.loadUserByUsername(principal.getName());
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("nav-user-table", userService.getAll());
        model.addAttribute("userRoles", userActive.getRoles());
        model.addAttribute("userActive", userActive);
        model.addAttribute("listRoles", listRoles);
        return "admin";

    }

    @GetMapping("/user")
    public String index(@AuthenticationPrincipal User userActive, Model model) {
        model.addAttribute("userActive", userActive);
        return "user";
    }


}
