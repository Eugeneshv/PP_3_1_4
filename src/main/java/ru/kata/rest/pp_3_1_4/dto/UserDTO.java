package ru.kata.rest.pp_3_1_4.dto;

import ru.kata.rest.pp_3_1_4.entity.Role;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private String username;
    private String password;
    private Set<String> roles;

    public UserDTO() {}

    public UserDTO(Long id, String firstname, String lastname, int age, String username, String password, Set<String> roles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}


