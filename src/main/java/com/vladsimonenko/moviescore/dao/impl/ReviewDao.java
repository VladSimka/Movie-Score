package com.vladsimonenko.moviescore.dao.impl;

import com.vladsimonenko.moviescore.model.Review;
import com.vladsimonenko.moviescore.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    static List<Review> getReviews(Long id, String findAllReviewsBySomeIdSql) {
        List<Review> reviews = new ArrayList<>();
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(findAllReviewsBySomeIdSql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Review review = Review.builder()
                        .id(resultSet.getLong("id"))
                        .userId(resultSet.getLong("user_id"))
                        .filmId(resultSet.getLong("film_id"))
                        .grade(resultSet.getInt("grade"))
                        .build();
                reviews.add(review);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }
}
