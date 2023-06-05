package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MovieGenreDTO;
import com.example.biblioteca.model.entity.MovieGenre;

import java.util.List;

public interface IMovieGenreMapper {
    MovieGenre dtoToEntity (MovieGenreDTO genreDTO);
    MovieGenreDTO entityToDto (MovieGenre genre);
    List<MovieGenreDTO> entityToDtoList (List<MovieGenre> genre);
}
