package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface IMovieService {

    List<Movie> findAll ();
    Optional<Movie> save(Movie movie);
    Boolean delete(Long id);
    Optional<Movie> update (Movie movie);
    Optional<Movie> findById (Long id);
    List<Movie> findAllByGenre(MovieGenre genre);
    List<Movie> findAllByDirector (Director director);
    List<Movie> findAllByActor (Actor actor);

    List<Movie> findByTitleContainsIgnoreCase(String title);
    List<Movie> findMovieByDirector(String name,String surnames);
    List<Movie> findMovieByActor(String name,String surnames);

    List<Movie> findMovieByGenre (MovieGenre genre);

    List<Movie> findMovieByLanguage (Language language);



}
