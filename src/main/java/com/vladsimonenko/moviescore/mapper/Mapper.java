package com.vladsimonenko.moviescore.mapper;

import java.util.List;

public interface Mapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);

    List<D> toDto(List<E> entities);
}
