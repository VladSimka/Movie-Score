package com.vladsimonenko.moviescore.command.impl;

import com.vladsimonenko.moviescore.command.Command;
import com.vladsimonenko.moviescore.service.UserService;
import com.vladsimonenko.moviescore.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = UserServiceImpl.getInstance();
        String page = "controller?command=default";

        if (userService.authenticate(username, password)) {
            request.getSession().setAttribute("user", userService.getByUsername(username));
        }
        return page;
    }
}
