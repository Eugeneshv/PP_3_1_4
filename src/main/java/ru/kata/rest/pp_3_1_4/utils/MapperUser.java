package ru.kata.rest.pp_3_1_4.utils;

import org.springframework.stereotype.Component;
import ru.kata.rest.pp_3_1_4.dto.UserDTO;
import ru.kata.rest.pp_3_1_4.entity.Role;
import ru.kata.rest.pp_3_1_4.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MapperUser {

    public UserDTO toUserDTO (User user) {
        Set<String> roleDTO = user.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toSet());
        UserDTO toDTO = new UserDTO (
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getAge(),
                user.getUsername(),
                user.getPassword(),
                roleDTO
        );
        return toDTO;
    }

}
