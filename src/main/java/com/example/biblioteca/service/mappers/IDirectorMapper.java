package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.DirectorDTO;
import com.example.biblioteca.model.entity.Director;

import java.util.List;

public interface IDirectorMapper {

    Director dtoToEntity (DirectorDTO directorDTO);
    DirectorDTO entityToDto (Director director);
    List<DirectorDTO> entityToDtoList (List<Director> director);
    List<Director> dtoListToEntity (List<DirectorDTO> directorsDTO);

}
