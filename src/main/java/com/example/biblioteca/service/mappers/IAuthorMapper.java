package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.AuthorDTO;
import com.example.biblioteca.model.entity.Author;

import java.util.List;

public interface IAuthorMapper {

    Author dtoToEntity (AuthorDTO authorDTO);
    AuthorDTO entityToDto (Author author);
    List<AuthorDTO> entityToDtoList (List<Author> author);

    List<Author> dtoListToEntity (List<AuthorDTO> authorsDTO);

}
