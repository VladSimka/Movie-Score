package com.vladsimonenko.moviescore.mapper;

import com.vladsimonenko.moviescore.dto.UserDto;
import com.vladsimonenko.moviescore.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper implements Mapper<User, UserDto> {
    @Getter
    private static final UserMapper instance = new UserMapper();

    private UserMapper() {

    }

    @Override
    public User toEntity(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .reviews(new ArrayList<>())
                .build();
    }

    @Override
    public UserDto toDto(User entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }

    @Override
    public List<UserDto> toDto(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
