package com.vladsimonenko.moviescore.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Film {
    private Long id;

    private String title;

    private String description;

    private double averageGrade;

    @ToString.Exclude
    private String pathToImage;

    @ToString.Exclude
    List<Review> reviews;
}
