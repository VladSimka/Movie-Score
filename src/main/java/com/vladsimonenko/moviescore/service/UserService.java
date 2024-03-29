package com.vladsimonenko.moviescore.service;

import com.vladsimonenko.moviescore.exception.ValidationException;
import com.vladsimonenko.moviescore.model.User;

public interface UserService {

    boolean authenticate(String username, String password);

    User getById(Long id);

    User getByUsername(String username);

    User create(User user) throws ValidationException;

    void addReview(User user, Long aLong, Long review) throws ValidationException;
}
