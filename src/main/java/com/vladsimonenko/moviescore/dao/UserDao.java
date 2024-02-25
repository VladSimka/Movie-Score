package com.vladsimonenko.moviescore.dao;

import com.vladsimonenko.moviescore.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    User save(User user);
}
