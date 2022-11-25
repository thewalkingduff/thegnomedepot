package com.devduffy.gnomedepot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
    Optional<User> findById(Integer id);
    List<User> findByUsername(String username);
    List<User> findByFirstNameAndLastNameIgnoreCase(String firstname, String lastname);
    List<User> findByState(String state);
    List<User> findByCountry(String country);
    List<User> findByEmail(String email);
    List<User> findByPhone(String phone);
}
