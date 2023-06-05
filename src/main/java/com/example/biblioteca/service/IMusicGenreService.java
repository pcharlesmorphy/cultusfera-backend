package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.MusicGenre;

import java.util.List;
import java.util.Optional;

public interface IMusicGenreService {
    List<MusicGenre> findAll ();
    Optional<MusicGenre> findById (Long id);
}
