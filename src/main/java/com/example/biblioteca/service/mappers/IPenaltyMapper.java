package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.PenaltyDTO;
import com.example.biblioteca.model.entity.Penalty;

import java.util.List;

public interface IPenaltyMapper {
    Penalty dtoToEntity (PenaltyDTO penaltyDTO);
    PenaltyDTO entityToDto (Penalty penalty);
    List<PenaltyDTO> entityToDtoList (List<Penalty> penalties);
    List<Penalty> dtoListToEntity (List<PenaltyDTO> penaltiesDTO);
}
