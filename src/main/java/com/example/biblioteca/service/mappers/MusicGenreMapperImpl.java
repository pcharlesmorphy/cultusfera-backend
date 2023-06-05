package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MovieGenreDTO;
import com.example.biblioteca.model.dto.MusicGenreDTO;
import com.example.biblioteca.model.entity.MovieGenre;
import com.example.biblioteca.model.entity.MusicGenre;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MusicGenreMapperImpl implements IMusicGenreMapper{
    @Override
    public MusicGenre dtoToEntity(MusicGenreDTO genreDTO) {
        MusicGenre genre = new MusicGenre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        return genre;
    }

    @Override
    public MusicGenreDTO entityToDto(MusicGenre genre) {
        MusicGenreDTO genreDTO = new MusicGenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }

    @Override
    public List<MusicGenreDTO> entityToDtoList(List<MusicGenre> genre) {
        return null;
    }
}
