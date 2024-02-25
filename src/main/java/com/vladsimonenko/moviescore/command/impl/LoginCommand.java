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
        String page = null;

        if (userService.authenticate(username, password)) {
            page = "jsp/main.jsp";
            request.getSession().setAttribute("user", userService.getByUsername(username));
        } else {
            page = "jsp/main.jsp";
        }
        return page;
    }
}
