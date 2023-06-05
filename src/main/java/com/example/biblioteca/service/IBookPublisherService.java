package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.BookPublisher;

import java.util.List;
import java.util.Optional;

public interface IBookPublisherService {

    List<BookPublisher> findAll ();
    Optional<BookPublisher> save(BookPublisher bookPublisher);
    Boolean delete(Long id);
    Optional<BookPublisher> update (BookPublisher bookPublisher);
    Optional<BookPublisher> findById (Long id);


}
