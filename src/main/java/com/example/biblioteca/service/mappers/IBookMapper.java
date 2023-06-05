package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.BookDTO;
import com.example.biblioteca.model.entity.Book;

import java.util.List;

public interface IBookMapper {

    Book dtoToEntity (BookDTO bookDTO);
    BookDTO entityToDto (Book book);
    List<BookDTO> entityToDtoList (List<Book> books);

}
