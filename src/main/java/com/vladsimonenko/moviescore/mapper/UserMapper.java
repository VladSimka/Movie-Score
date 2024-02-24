package com.vladsimonenko.moviescore.mapper;

import com.vladsimonenko.moviescore.dto.UserDto;
import com.vladsimonenko.moviescore.model.User;

public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public User toEntity(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    @Override
    public UserDto toDto(User entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .build();
    }
}
