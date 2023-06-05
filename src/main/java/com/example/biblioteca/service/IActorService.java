package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Actor;
import com.example.biblioteca.model.entity.Movie;
import com.example.biblioteca.model.entity.Writer;

import java.util.List;
import java.util.Optional;

public interface IActorService {
    List<Actor> findAll ();
    List<Actor> findAllByMovies(Movie movie);

    Optional<Actor> save(Actor actor);
    Boolean delete(Long id);
    Optional<Actor> update (Actor actor);
    Optional<Actor> findById (Long id);

}
