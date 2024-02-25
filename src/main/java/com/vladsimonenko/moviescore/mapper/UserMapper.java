package com.vladsimonenko.moviescore.mapper;

import com.vladsimonenko.moviescore.dto.UserDto;
import com.vladsimonenko.moviescore.model.User;
import lombok.Getter;

public class UserMapper implements Mapper<User, UserDto> {
    @Getter
    private static final UserMapper instance = new UserMapper();
    private UserMapper(){

    }
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
