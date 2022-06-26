package ru.kata.rest.pp_3_1_4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.rest.pp_3_1_4.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findByUsername(String username);
}
