package com.devduffy.gnomedepot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findById(Integer id);
    List<User> findByUsername(String username);
    List<User> findByUserFirstNameAndLastName(String firstname, String lastname);
    List<User> findByState(String state);
    List<User> findByCountry(String country);
    List<User> findByEmail(String email);
    List<User> findByPhone(String phone);
}
