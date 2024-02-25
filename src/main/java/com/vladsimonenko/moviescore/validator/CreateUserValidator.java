package com.vladsimonenko.moviescore.validator;

import com.vladsimonenko.moviescore.dto.UserDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<UserDto> {
    @Getter
    private static final CreateUserValidator instance = new CreateUserValidator();


    @Override
    public ValidationResult isValid(UserDto userDto) {
        var validationResult = new ValidationResult();

        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()
            || userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            validationResult.add(Error.of("invalid.register", "Username or password is empty"));
        }

        if (userDto.getUsername() != null && userDto.getUsername().length() < 4
            || userDto.getPassword() != null && userDto.getPassword().length() < 4) {
            validationResult.add(Error.of("invalid.register", "Username or password is too short"));
        }

        return validationResult;
    }
}
