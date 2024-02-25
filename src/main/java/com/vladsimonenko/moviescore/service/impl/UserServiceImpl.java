package com.vladsimonenko.moviescore.service.impl;

import com.vladsimonenko.moviescore.dao.UserDao;
import com.vladsimonenko.moviescore.dao.impl.UserDaoImpl;
import com.vladsimonenko.moviescore.exception.ResourceNotFoundException;
import com.vladsimonenko.moviescore.model.User;
import com.vladsimonenko.moviescore.service.UserService;
import lombok.Getter;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Getter
    private static final UserServiceImpl instance = new UserServiceImpl();
    private final UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    @Override
    public boolean authenticate(String username, String password) {
        Optional<User> user = userDao.findByUsername(username);
        return user.stream()
                .anyMatch(u -> u.getPassword().equals(password));
    }

    @Override
    public User getById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find user"));
    }

    @Override
    public User getByUsername(String username) {
        return userDao.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find user"));
    }
}
