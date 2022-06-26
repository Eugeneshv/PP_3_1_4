package ru.kata.rest.pp_3_1_4.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.rest.pp_3_1_4.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
