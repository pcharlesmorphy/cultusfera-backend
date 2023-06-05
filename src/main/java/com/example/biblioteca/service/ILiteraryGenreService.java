package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.LiteraryGenre;

import java.util.List;
import java.util.Optional;

public interface ILiteraryGenreService {
    List<LiteraryGenre> findAll ();
    Optional<LiteraryGenre> findById (Long id);
}
