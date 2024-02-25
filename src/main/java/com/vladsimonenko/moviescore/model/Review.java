package com.vladsimonenko.moviescore.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Review {
    private Long id;

    private int grade;

    private Long filmId;

    private Long userId;
}
