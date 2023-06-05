package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.CopyResourceDTO;
import com.example.biblioteca.model.entity.Copy;

import java.util.List;

public interface ICopyResourceMapper {
    Copy dtoToEntity (CopyResourceDTO copyDTO);
    CopyResourceDTO entityToDto (Copy copy);
    List<CopyResourceDTO> entityToDtoList (List<Copy> copies);
    List<Copy> dtoListToEntity (List<CopyResourceDTO> copiesDTO);
}
