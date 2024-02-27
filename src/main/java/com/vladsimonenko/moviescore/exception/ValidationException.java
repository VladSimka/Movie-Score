package com.vladsimonenko.moviescore.exception;

import com.vladsimonenko.moviescore.validator.Error;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Getter
public class ValidationException extends Exception{
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = new ArrayList<>(errors);
    }
}
