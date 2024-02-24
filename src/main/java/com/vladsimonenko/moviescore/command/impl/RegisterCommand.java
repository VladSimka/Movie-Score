package com.vladsimonenko.moviescore.command.impl;

import com.vladsimonenko.moviescore.command.Command;
import com.vladsimonenko.moviescore.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            UserDto userDto = UserDto.builder()
                    .username(username)
                    .password(password)
                    .build();
            request.setAttribute("userDto", userDto);
            request.setAttribute("is_registred", true);
            return "jsp/main.jsp";
        }
        return null;
    }
}
