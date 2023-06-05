package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.CopyDTO;
import com.example.biblioteca.model.entity.Copy;

import java.util.List;

public interface ICopyMapper {
    Copy dtoToEntity (CopyDTO copyDTO);
    CopyDTO entityToDto (Copy copy);
    List<CopyDTO> entityToDtoList (List<Copy> copy);
    List<Copy> dtoListToEntity (List<CopyDTO> copiesDTO);
}
