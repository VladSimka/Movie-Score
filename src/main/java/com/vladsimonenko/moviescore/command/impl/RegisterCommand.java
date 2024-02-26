package com.vladsimonenko.moviescore.command.impl;

import com.vladsimonenko.moviescore.command.Command;
import com.vladsimonenko.moviescore.dto.UserDto;
import com.vladsimonenko.moviescore.exception.ValidationException;
import com.vladsimonenko.moviescore.mapper.UserMapper;
import com.vladsimonenko.moviescore.model.User;
import com.vladsimonenko.moviescore.service.UserService;
import com.vladsimonenko.moviescore.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    private final UserMapper userMapper = UserMapper.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDto built = UserDto.builder()
                .username(username)
                .password(password)
                .build();
        String page = null;
        try {
            User user = userService.create(userMapper.toEntity(built));
            page = "jsp/main.jsp";
            request.getSession().setAttribute("user", user);
        } catch (ValidationException e) {
            page = "auth/register.jsp";
            request.setAttribute("errors", e.getErrors());
        }
        return page;
    }
}
