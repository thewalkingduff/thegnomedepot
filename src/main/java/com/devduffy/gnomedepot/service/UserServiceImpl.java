package com.devduffy.gnomedepot.service;

import org.springframework.stereotype.Service;

import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
}
