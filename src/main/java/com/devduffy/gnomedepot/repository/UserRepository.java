package com.devduffy.gnomedepot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findById(Integer id);
    User findByUsername(String username);
    List<User> findByFirstNameAndLastNameIgnoreCase(String firstname, String lastname);
    User findByEmail(String email);
}
