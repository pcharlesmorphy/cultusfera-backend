package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MagazineDTO;
import com.example.biblioteca.model.entity.Magazine;

import java.util.List;

public interface IMagazineMapper {
    Magazine dtoToEntity (MagazineDTO magazineDTO);
    MagazineDTO entityToDto (Magazine magazine);
    List<MagazineDTO> entityToDtoList (List<Magazine> magazines);
}
