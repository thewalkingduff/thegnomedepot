package com.devduffy.gnomedepot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.devduffy.gnomedepot.entity.Order;
import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.exception.UserNotFoundException;
import com.devduffy.gnomedepot.repository.UserRepository;
import com.devduffy.gnomedepot.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public List<Order> getOrders(Integer id) {
        User user = getUser(id);
        return user.getOrders();
    }

    static User unwrapUser(Optional<User> entity, Integer id) {
        if (entity.isPresent()) return entity.get();
        else throw new UserNotFoundException(id);
    }

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

    @Override
    public Boolean isUserAuthenticated(String email) {
        if(getUserByEmail(email) != null) {
            return true;
        }
        return false;
    }
    
}
