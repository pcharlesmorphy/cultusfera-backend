package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MovieDTO;
import com.example.biblioteca.model.entity.Movie;

import java.util.List;

public interface IMovieMapper {
    Movie dtoToEntity (MovieDTO movieDTO);
    MovieDTO entityToDto (Movie movie);
    List<MovieDTO> entityToDtoList (List<Movie> movies);
}
