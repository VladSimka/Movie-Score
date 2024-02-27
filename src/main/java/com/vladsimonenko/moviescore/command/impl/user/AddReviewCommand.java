package com.vladsimonenko.moviescore.command.impl.user;

import com.vladsimonenko.moviescore.command.Command;
import com.vladsimonenko.moviescore.exception.ValidationException;
import com.vladsimonenko.moviescore.model.User;
import com.vladsimonenko.moviescore.service.UserService;
import com.vladsimonenko.moviescore.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class AddReviewCommand implements Command {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String filmId = request.getParameter("film_id");
        String review = request.getParameter("selection");

        User user = (User) request.getSession().getAttribute("user");

        try {
            userService.addReview(user, Long.valueOf(filmId), Long.valueOf(review));
        } catch (ValidationException e) {
            request.setAttribute("errors", e.getErrors());
        }


        return "controller?command=go_to_film_page&film_id=" + filmId;
    }
}
