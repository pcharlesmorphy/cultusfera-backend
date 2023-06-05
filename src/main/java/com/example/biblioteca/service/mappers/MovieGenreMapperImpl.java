package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MovieGenreDTO;
import com.example.biblioteca.model.entity.MovieGenre;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieGenreMapperImpl implements IMovieGenreMapper {
    @Override
    public MovieGenre dtoToEntity(MovieGenreDTO genreDTO) {
        MovieGenre genre = new MovieGenre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        return genre;
    }

    @Override
    public MovieGenreDTO entityToDto(MovieGenre genre) {
        MovieGenreDTO genreDTO = new MovieGenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }

    @Override
    public List<MovieGenreDTO> entityToDtoList(List<MovieGenre> genre) {
        return null;
    }
}


