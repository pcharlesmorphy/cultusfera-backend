package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book,Long> {

    List<Book> findByTitleContainsIgnoreCase  (String title);

    List<Book> findAllByTitleEqualsIgnoreCase (String title);
    List<Book> findByIsbnContainsIgnoreCase (String isbn);
    List<Book> findAllByWriters(Writer writer);

    List<Book> findAllByGenre(LiteraryGenre genre);

    List<Book> findAllByLanguage (Language language);

    List<Book> findAllByPublisher (BookPublisher publisher);

}
