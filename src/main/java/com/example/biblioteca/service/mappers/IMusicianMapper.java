package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MusicianDTO;
import com.example.biblioteca.model.entity.Musician;

import java.util.List;

public interface IMusicianMapper {
    Musician dtoToEntity (MusicianDTO musicianDTO);
    MusicianDTO entityToDto (Musician musician);
    List<MusicianDTO> entityToDtoList (List<Musician> musicians);
    List<Musician> dtoListToEntity (List<MusicianDTO> musiciansDTO);
}
