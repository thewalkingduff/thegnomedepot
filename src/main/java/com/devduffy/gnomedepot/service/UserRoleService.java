package com.devduffy.gnomedepot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.devduffy.gnomedepot.entity.UserRole;
import com.devduffy.gnomedepot.repository.UserRoleRepository;

public interface UserRoleService {
    
   List<UserRole> getAllRoles();

   UserRole getUserRoleById(Integer userId);

   UserRole getUserRoleByName(String name);

}
