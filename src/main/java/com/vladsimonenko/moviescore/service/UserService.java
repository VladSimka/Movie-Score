package com.vladsimonenko.moviescore.service;

import com.vladsimonenko.moviescore.model.User;

public interface UserService {

    boolean authenticate(String username, String password);

    User getById(Long id);

    User getByUsername(String username);
}
