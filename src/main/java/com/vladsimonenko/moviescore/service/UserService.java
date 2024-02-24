package com.vladsimonenko.moviescore.service;

public interface UserService {

    boolean authenticate(String username, String password);
}
