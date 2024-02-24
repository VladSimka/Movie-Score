package com.vladsimonenko.moviescore.command.impl;

import com.vladsimonenko.moviescore.command.Command;
import com.vladsimonenko.moviescore.dao.FilmDao;
import com.vladsimonenko.moviescore.dao.impl.FilmDaoImpl;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    private final FilmDao filmDao = new FilmDaoImpl();

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("films", filmDao.findAll());
        return "jsp/main.jsp";
    }
}
