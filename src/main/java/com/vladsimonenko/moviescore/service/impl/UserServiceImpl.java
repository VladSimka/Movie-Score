package com.vladsimonenko.moviescore.service.impl;

import com.vladsimonenko.moviescore.service.UserService;

import java.util.Objects;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String username, String password) {
        return Objects.equals(username, password);
    }
}
