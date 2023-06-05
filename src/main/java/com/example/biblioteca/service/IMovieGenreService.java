package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.MovieGenre;

import java.util.List;
import java.util.Optional;

public interface IMovieGenreService {

    List<MovieGenre> findAll ();
    Optional<MovieGenre> findById (Long id);

}
