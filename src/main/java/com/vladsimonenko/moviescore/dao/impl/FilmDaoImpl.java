package com.vladsimonenko.moviescore.dao.impl;

import com.vladsimonenko.moviescore.dao.FilmDao;
import com.vladsimonenko.moviescore.model.Film;
import com.vladsimonenko.moviescore.model.Review;
import com.vladsimonenko.moviescore.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmDaoImpl implements FilmDao {
    @Getter
    private static final FilmDao instance = new FilmDaoImpl();

    private static final String FIND_ALL_FILMS_SQL = """
            SELECT *
            FROM films
            """;

    private static final String FIND_ALL_REVIEWS_BY_FILM_ID_SQL = """
                        SELECT *
                        FROM reviews
                        WHERE film_id = ?
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT *
            FROM films
            WHERE id = ?
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
                        .pathToImage(resultSet.getString("path_to_image"))
                        .build();
                film.setReviews(ReviewDao.getReviews(film.getId(), FIND_ALL_REVIEWS_BY_FILM_ID_SQL));
                var average = film.getReviews().stream()
                        .mapToInt(Review::getGrade).average()
                        .orElse(0);
                film.setAverageGrade(average);
                films.add(film);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return films;
    }

    @Override
    public Optional<Film> findById(Long id) {
        Film film = null;
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                film = Film.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .pathToImage(resultSet.getString("path_to_image"))
                        .build();
                film.setReviews(ReviewDao.getReviews(film.getId(), FIND_ALL_REVIEWS_BY_FILM_ID_SQL));
                var average = film.getReviews().stream()
                        .mapToInt(Review::getGrade).average()
                        .orElse(0);
                film.setAverageGrade(average);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return Optional.ofNullable(film);
    }


}
