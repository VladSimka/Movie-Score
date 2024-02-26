package com.vladsimonenko.moviescore.service.impl;

import com.vladsimonenko.moviescore.dao.FilmDao;
import com.vladsimonenko.moviescore.dao.impl.FilmDaoImpl;
import com.vladsimonenko.moviescore.exception.ResourceNotFoundException;
import com.vladsimonenko.moviescore.model.Film;
import com.vladsimonenko.moviescore.service.FilmService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmServiceImpl implements FilmService {
    @Getter
    private static final FilmService instance = new FilmServiceImpl();

    private final FilmDao filmDao = FilmDaoImpl.getInstance();

    @Override
    public Film getById(Long filmId) {
        return filmDao.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("Can not find film"));
    }

    @Override
    public List<Film> getAllFilms() {
        return filmDao.findAll();
    }
}
