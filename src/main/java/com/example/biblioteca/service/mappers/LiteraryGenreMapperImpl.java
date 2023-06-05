package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.LiteraryGenreDTO;
import com.example.biblioteca.model.entity.LiteraryGenre;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LiteraryGenreMapperImpl implements ILiteraryGenreMapper {
    @Override
    public LiteraryGenre dtoToEntity(LiteraryGenreDTO generoDTO) {
        LiteraryGenre genero = new LiteraryGenre();
        genero.setId(generoDTO.getId());
        genero.setName(generoDTO.getName());
        return genero;
    }

    @Override
    public LiteraryGenreDTO entityToDto(LiteraryGenre genero) {
        LiteraryGenreDTO generoDTO = new LiteraryGenreDTO();
        generoDTO.setId(genero.getId());
        generoDTO.setName(genero.getName());
        return generoDTO;
    }

    @Override
    public List<LiteraryGenreDTO> entityToDtoList(List<LiteraryGenre> genero) {
        return null;
    }
}
