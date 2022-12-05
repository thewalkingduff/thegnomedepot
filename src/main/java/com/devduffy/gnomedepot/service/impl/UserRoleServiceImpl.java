package com.devduffy.gnomedepot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.devduffy.gnomedepot.entity.UserRole;
import com.devduffy.gnomedepot.repository.UserRoleRepository;
import com.devduffy.gnomedepot.service.UserRoleService;

public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> getAllRoles() {
        return (List<UserRole>) userRoleRepository.findAll();
    }

    @Override
    public UserRole getUserRoleById(Integer id) {
        return userRoleRepository.findById(id);
    }

    @Override
    public UserRole getUserRoleByName(String name) {
        return userRoleRepository.findByRoleName(name);
    }

}
