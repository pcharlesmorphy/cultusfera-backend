package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MagazinePublisherDTO;
import com.example.biblioteca.model.dto.LiteraryGenreDTO;
import com.example.biblioteca.model.entity.MagazinePublisher;
import com.example.biblioteca.model.entity.LiteraryGenre;

import java.util.List;

public interface IMagazinePublisherMapper {
    MagazinePublisher dtoToEntity (MagazinePublisherDTO publisherDTO);
    MagazinePublisherDTO entityToDto (MagazinePublisher publisher);
    List<MagazinePublisherDTO> entityToDtoList (List<MagazinePublisher> publishers);
}
