package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.AlbumDTO;
import com.example.biblioteca.model.entity.Album;

import java.util.List;

public interface IAlbumMapper {
    Album dtoToEntity (AlbumDTO albumDTO);
    AlbumDTO entityToDto (Album album);
    List<AlbumDTO> entityToDtoList (List<Album> albums);
}

