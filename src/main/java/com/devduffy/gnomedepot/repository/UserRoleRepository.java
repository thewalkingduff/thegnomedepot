package com.devduffy.gnomedepot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.devduffy.gnomedepot.entity.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Long>{
    public List<UserRole> findByUserId(Integer userId);
    UserRole findByRoleName(String roleName);
    public UserRole findById(Integer id);
}
