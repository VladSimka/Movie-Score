package com.vladsimonenko.moviescore.service;

import com.vladsimonenko.moviescore.model.Film;

import java.util.List;

public interface FilmService {

    Film getById(Long filmId);

    List<Film> getAllFilms();
}
