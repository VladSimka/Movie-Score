package com.vladsimonenko.moviescore.dao.impl;

import com.vladsimonenko.moviescore.dao.FilmDao;
import com.vladsimonenko.moviescore.model.Film;
import com.vladsimonenko.moviescore.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDaoImpl implements FilmDao {

    private static final String FIND_ALL_FILMS_SQL = """
            SELECT *
            FROM films
            """;

    @Override
    public List<Film> findAll() {
        List<Film> films = new ArrayList<>();
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_FILMS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Film film = Film.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .averageGrade(resultSet.getDouble("average_grade"))
                        .build();

                films.add(film);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return films;
    }
}
