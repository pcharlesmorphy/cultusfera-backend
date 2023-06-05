package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    
    List<Book> findAll ();
    Optional<Book> save(Book book);
    Boolean delete(Long id);
    Optional<Book> update (Book book);
    Optional<Book> findById (Long id);

    List<Book> findByTitleContainsIgnoreCase(String title);

    List<Book> findAllByWriters(Writer writer);
    List<Book> findByIsbnContainsIgnoreCase (String isbn);

    List<Book> findBookByWriter(String name, String surnames);

    List<Book> findBookByGenre (LiteraryGenre genre);

    List<Book> findBookByLanguage (Language language);

    List<Book> findBooksByPublisher (BookPublisher publisher);

}
