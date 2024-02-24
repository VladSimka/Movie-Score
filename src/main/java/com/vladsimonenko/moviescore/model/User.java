package com.vladsimonenko.moviescore.model;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;

    private String username;

    private String password;

    private Set<Role> roles;

    private List<Review> reviews;

    private List<Film> likedFilms;
}


