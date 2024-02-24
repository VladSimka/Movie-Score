package com.vladsimonenko.moviescore.dao;

import com.vladsimonenko.moviescore.model.Film;

import java.util.List;

public interface FilmDao {
    List<Film> findAll();
}
