package com.vladsimonenko.moviescore.dao.impl;

import com.vladsimonenko.moviescore.dao.UserDao;
import com.vladsimonenko.moviescore.model.User;
import com.vladsimonenko.moviescore.util.ConnectionManager;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Getter
    private static final UserDao instance = new UserDaoImpl();

    private static final String FIND_BY_ID_SQL = """
            SELECT *
            FROM users
            WHERE id = ?
            """;
    private static final String FIND_BY_USERNAME_SQL = """
            SELECT *
            FROM users
            WHERE username = ?
            """;

    private static final String FIND_ALL_REVIEWS_BY_USER_ID = """
            SELECT *
            FROM reviews
            WHERE user_id = ?
            """;

    private static final String SAVE_USER_SQL = """
            INSERT INTO users (username, password)
            VALUES (?,?)
            """;

    private UserDaoImpl() {
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = null;
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_USERNAME_SQL)) {
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong("id"))
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .build();

                user.setReviews(ReviewDao.getReviews(user.getId(), FIND_ALL_REVIEWS_BY_USER_ID));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return Optional.ofNullable(user);
    }

    @Override
    public User save(User user) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_USER_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.execute();

            if (preparedStatement.getGeneratedKeys().next()) {
                user.setId(preparedStatement.getGeneratedKeys().getLong("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }


}
