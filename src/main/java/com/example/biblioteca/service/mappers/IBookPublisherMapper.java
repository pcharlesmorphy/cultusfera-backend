package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.BookPublisherDTO;
import com.example.biblioteca.model.entity.BookPublisher;

import java.util.List;

public interface IBookPublisherMapper {
    BookPublisher dtoToEntity (BookPublisherDTO publisherDTO);
    BookPublisherDTO entityToDto (BookPublisher publisher);
    List<BookPublisherDTO> entityToDtoList (List<BookPublisher> publishers);
}
