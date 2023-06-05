package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Director;

import java.util.List;
import java.util.Optional;

public interface IDirectorService {
    List<Director> findAll();  
    Optional<Director> save(Director director);
    Boolean delete(Long id);
    Optional<Director> update (Director director);
    Optional<Director> findById (Long id);

}

