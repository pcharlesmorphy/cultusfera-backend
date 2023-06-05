package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    List<Author> findAll ();
    Author save(Author author);
    Boolean delete(Long id);
    Author update (Author author);
    Optional<Author> findById (Long id);

    List<Author> findAllByRole(String type);

}
