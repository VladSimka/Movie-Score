package com.vladsimonenko.moviescore.mapper;

import com.vladsimonenko.moviescore.dto.FilmDto;
import com.vladsimonenko.moviescore.model.Film;
import lombok.Getter;

public class FilmMapper implements Mapper<Film, FilmDto> {
    @Getter
    private static final FilmMapper instance = new FilmMapper();
    private FilmMapper(){

    }
    @Override
    public Film toEntity(FilmDto dto) {
        return Film.builder()
                .title(dto.getTitle())
                .averageGrade(dto.getAverageGrade())
                .description(dto.getDescription())
                .pathToImage(dto.getPathToImage())
                .build();
    }

    @Override
    public FilmDto toDto(Film entity) {
        return FilmDto.builder()
                .title(entity.getTitle())
                .averageGrade(entity.getAverageGrade())
                .description(entity.getDescription())
                .pathToImage(entity.getPathToImage())
                .build();
    }
}
