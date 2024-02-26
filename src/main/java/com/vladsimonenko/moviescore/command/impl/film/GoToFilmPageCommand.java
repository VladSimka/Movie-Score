package com.vladsimonenko.moviescore.command.impl.film;

import com.vladsimonenko.moviescore.command.Command;
import com.vladsimonenko.moviescore.mapper.FilmMapper;
import com.vladsimonenko.moviescore.model.Film;
import com.vladsimonenko.moviescore.service.FilmService;
import com.vladsimonenko.moviescore.service.impl.FilmServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class GoToFilmPageCommand implements Command {
    private final FilmService filmService = FilmServiceImpl.getInstance();
    private final FilmMapper filmMapper = FilmMapper.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String strId = request.getParameter("id");
        if (strId != null) {
            Long id = Long.valueOf(strId);
            Film film = filmService.getById(id);
            request.setAttribute("film", filmMapper.toDto(film));
            page = "jsp/film.jsp";
        } else {
            page = "error/error.jsp";
        }
        return page;
    }
}
