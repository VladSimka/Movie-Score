package com.vladsimonenko.moviescore.command.impl.user;

import com.vladsimonenko.moviescore.command.Command;
import com.vladsimonenko.moviescore.model.User;
import com.vladsimonenko.moviescore.service.UserService;
import com.vladsimonenko.moviescore.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToUserPageCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (session.getAttribute("user") != null) {
            page = "jsp/user.jsp";
        } else {
            page = "error/error.jsp";
        }
        return page;
    }
}
