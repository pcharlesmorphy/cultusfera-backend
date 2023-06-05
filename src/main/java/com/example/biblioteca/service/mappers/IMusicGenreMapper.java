package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MusicGenreDTO;
import com.example.biblioteca.model.entity.MusicGenre;

import java.util.List;

public interface IMusicGenreMapper {

    MusicGenre dtoToEntity (MusicGenreDTO genreDTO);
    MusicGenreDTO entityToDto (MusicGenre genre);
    List<MusicGenreDTO> entityToDtoList (List<MusicGenre> genre);
}
