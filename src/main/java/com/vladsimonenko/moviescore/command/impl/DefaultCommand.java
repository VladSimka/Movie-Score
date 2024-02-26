package com.vladsimonenko.moviescore.command.impl;

import com.vladsimonenko.moviescore.command.Command;
import com.vladsimonenko.moviescore.service.FilmService;
import com.vladsimonenko.moviescore.service.impl.FilmServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    private final FilmService filmService = FilmServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("films", filmService.getAllFilms());
        return "jsp/main.jsp";
    }
}
