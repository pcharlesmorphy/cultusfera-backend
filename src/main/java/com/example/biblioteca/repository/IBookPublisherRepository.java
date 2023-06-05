package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.BookPublisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookPublisherRepository extends JpaRepository<BookPublisher,Long> {
        BookPublisher findBookPublisherByName (String name);
}
