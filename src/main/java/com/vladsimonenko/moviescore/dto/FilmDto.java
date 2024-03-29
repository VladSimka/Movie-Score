package com.vladsimonenko.moviescore.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class FilmDto {
    Long id;

    private String title;

    private String description;

    private double averageGrade;

    @ToString.Exclude
    private String pathToImage;
}
