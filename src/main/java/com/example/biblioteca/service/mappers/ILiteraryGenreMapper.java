package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.LiteraryGenreDTO;
import com.example.biblioteca.model.entity.LiteraryGenre;

import java.util.List;

public interface ILiteraryGenreMapper {
    LiteraryGenre dtoToEntity (LiteraryGenreDTO genreDTO);
    LiteraryGenreDTO entityToDto (LiteraryGenre genre);
    List<LiteraryGenreDTO> entityToDtoList (List<LiteraryGenre> genre);
}
