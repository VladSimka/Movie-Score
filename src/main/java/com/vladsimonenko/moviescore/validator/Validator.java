package com.vladsimonenko.moviescore.validator;

public interface Validator<T> {
    ValidationResult isValid(T t);
}
