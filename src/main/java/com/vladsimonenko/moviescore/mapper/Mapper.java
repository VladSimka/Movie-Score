package com.vladsimonenko.moviescore.mapper;

public interface Mapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);
}
