package com.vladsimonenko.moviescore.command.impl;

import com.vladsimonenko.moviescore.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();

        return "jsp/main.jsp";
    }
}
