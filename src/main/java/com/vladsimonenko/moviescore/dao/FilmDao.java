package com.vladsimonenko.moviescore.dao;

import com.vladsimonenko.moviescore.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmDao {
    List<Film> findAll();

    Optional<Film> findById(Long id);
}
