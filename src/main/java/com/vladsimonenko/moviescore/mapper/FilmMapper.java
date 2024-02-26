package com.vladsimonenko.moviescore.mapper;

import com.vladsimonenko.moviescore.dto.FilmDto;
import com.vladsimonenko.moviescore.model.Film;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class FilmMapper implements Mapper<Film, FilmDto> {
    @Getter
    private static final FilmMapper instance = new FilmMapper();

    private FilmMapper() {

    }

    @Override
    public FilmDto toDto(Film entity) {
        return FilmDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .averageGrade(entity.getAverageGrade())
                .description(entity.getDescription())
                .pathToImage(entity.getPathToImage())
                .build();
    }

    @Override
    public Film toEntity(FilmDto dto) {
        return Film.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .averageGrade(dto.getAverageGrade())
                .pathToImage(dto.getPathToImage())
                .build();
    }

    public List<FilmDto> toDto(List<Film> films) {
        return films.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
