package com.vladsimonenko.moviescore.service.impl;

import com.vladsimonenko.moviescore.dao.UserDao;
import com.vladsimonenko.moviescore.dao.impl.UserDaoImpl;
import com.vladsimonenko.moviescore.exception.ResourceNotFoundException;
import com.vladsimonenko.moviescore.exception.ValidationException;
import com.vladsimonenko.moviescore.mapper.UserMapper;
import com.vladsimonenko.moviescore.model.User;
import com.vladsimonenko.moviescore.service.UserService;
import com.vladsimonenko.moviescore.validator.CreateUserValidator;
import com.vladsimonenko.moviescore.validator.Error;
import com.vladsimonenko.moviescore.validator.ValidationResult;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Getter
    private static final UserServiceImpl instance = new UserServiceImpl();
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

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

    @Override
    public User create(User user) throws ValidationException {
        ValidationResult result = createUserValidator.isValid(userMapper.toDto(user));
        if (!result.isValid()) {
            throw new ValidationException(result.getErrors());
        }

        if (userDao.findByUsername(user.getUsername()).isPresent()) {
            throw new ValidationException(List.of(Error.of("invalid.register"
                    , "User with this username already exists")));
        }
        return userDao.save(user);
    }

    @Override
    public void addReview(User user, Long filmId, Long review) throws ValidationException {
        if (user.getReviews().isEmpty()) {
            userDao.addReview(user, filmId, review);
            return;
        }

        user.getReviews().stream()
                .filter(r -> r.getFilmId().equals(filmId)).findFirst()
                .orElseThrow(() ->
                        new ValidationException(List.of(Error.of("invalid.review",
                                "You can not add more than 1 review")))
                );

        userDao.addReview(user, filmId, review);
    }
}
