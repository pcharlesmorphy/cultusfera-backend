package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.PenaltyReasonDTO;
import com.example.biblioteca.model.entity.PenaltyReason;

import java.util.List;

public interface IPenaltyReasonMapper {
    PenaltyReason dtoToEntity (PenaltyReasonDTO reasonDTO);
    PenaltyReasonDTO entityToDto (PenaltyReason reason);
    List<PenaltyReasonDTO> entityToDtoList (List<PenaltyReason> reasons);
    List<PenaltyReason> dtoListToEntity (List<PenaltyReasonDTO> reasonsDTO);
}
